package su.kami.moyen.Model;

public class Return<T extends String> {
    private int code;
    private T content;

    public Return(){

    }
    public Return(int code, T content){
        this.code = code;
        this.content = content;
    }

    @Override
    public String toString(){
        return "{ \"code\": " + this.code +
                ", \"content\": " + this.content.toString() +
                "}";
    }

    public String New(int code, T content){
        this.code = code;
        this.content = content;
        return this.toString();
    }
    public String New(){
        this.code = 418;
        this.content = (T) "I'm a Teapot!";
        return this.toString();
    }
}
