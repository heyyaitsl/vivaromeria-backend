package tfg.romerias.common;

public class ValidationUtils {
    private ValidationUtils(){}

    public static void notNull(Object ...args){
        for(Object arg: args){
            if (arg==null){
                throw new IllegalArgumentException("Uno de los parámetros es nulo");
            }

        }
    }
    public static void notEmpty(String ...args){
        for(String arg: args){
            if (arg.isEmpty()){
                throw new IllegalArgumentException("Uno de los parámetros está vacío");
            }

        }
    }


}
