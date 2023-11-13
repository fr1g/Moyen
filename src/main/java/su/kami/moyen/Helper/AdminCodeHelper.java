package su.kami.moyen.Helper;

import org.springframework.beans.factory.annotation.Value;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;

public class AdminCodeHelper {
    @Value("${spring.datasource.url}")
    String url;

    @Value("${spring.datasource.username}")
    String username;

    @Value("${spring.datasource.password}")
    String pass;

    Connection connection;

    public String Code = "init";

    public void ApplyNew(String code){
        try {
            connection.prepareStatement("set sql_mode='no_auto_value_on_zero'").execute();
            var lang = "update users set contact = ? where id = 0";
            var prepare = connection.prepareStatement(lang);
            prepare.setString(1, MD5CryptHelper.encrypt(code)); // why starting at 1?
            prepare.execute();
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    private void PrintConnInfo(){
        System.out.println("=== CONN INFO ===");
        System.out.println("URL: " + url);
        System.out.println("USR: " + username);
        System.out.println("PWD: " + pass);
        System.out.println("=== ENDS INFO ===");
    }
    public AdminCodeHelper(){
        Initialize();
    }

    public void Initialize(){
        try {
            if(url == null){
                System.out.println("[init worker]: Failed to gather connection data from @Value, trying parse... ");
                var propContent = Files.readString(Path.of(System.getProperty("user.dir") + "/src/main/resources/application.properties"));
                var ParseForming
                        = propContent.split("# end of DB !!!")[0]
                        .replace("# these comments cannot be deleted\n# start of DB !!!", "")
                        .replace("\n", "")
                        .replace(" ", "")
                        .split("spring.");

                this.url = ParseForming[1].replace("datasource.url=", "");
                this.username = ParseForming[2].replace("datasource.username=", "");
                this.pass = ParseForming[3].replace("datasource.password=", "");
            }
            PrintConnInfo();
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, pass);
            connection.prepareStatement("set sql_mode='no_auto_value_on_zero'").execute();
            ForceCode();
        }catch (Exception ex){
            if(ex.toString().contains("empty") && (connection != null)) {
                ForceReset();
                Initialize();
            }
        }
    }

    public void ForceReset(){
        try {
            System.out.println("Detected no Admin exist...");
            connection.prepareStatement("truncate table users").execute();
            connection.prepareStatement("set sql_mode='no_auto_value_on_zero'").execute();
            var state = connection.prepareStatement("insert into users(id, name, contact, balance, elsewhat, status) value(0, 'root', ?, 0, 'root', 3)");
            state.setString(1, MD5CryptHelper.encrypt("default"));
            state.execute();
            System.out.println("=== your default login code is \"default\". Use this for initialized login. ===");
            return;
        }catch (Exception exc){
            System.out.println("??????");
            exc.printStackTrace();
        }

    }

    public String ForceCode(){
        try {
            connection.prepareStatement("set sql_mode='no_auto_value_on_zero'").execute();
            var lang = "select contact from users where id = 0";
            var result = connection.prepareStatement(lang).executeQuery();
            result.next();
            var code = result.getString(1);
            this.Code = code;
            return code;
        }catch (Exception ex){
            if(ex.toString().contains("empty") && (connection != null)) {
                ForceReset();
                return ForceCode();
            } else {
                ex.printStackTrace();
                return "e";
            }
        }
    }

    public void Vanish(){
        try {
            this.connection.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
