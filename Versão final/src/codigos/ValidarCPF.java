package codigos;

public class ValidarCPF {
    public boolean validaCpf (String cpfS1) {
        String cpfS = cpfS1.replaceAll("[^0123456789]", "");
        System.out.println(cpfS);
        int[] cpf = new int[11];
        int i;
        int j;
        int soma = 0;
        int aux;
        do {
         
            if (cpfS.length() != 11) {
                return false;
            }
            if (cpfS.equalsIgnoreCase("00000000000") || cpfS.equalsIgnoreCase("11111111111")
                || cpfS.equalsIgnoreCase("22222222222") || cpfS.equalsIgnoreCase("33333333333")
                || cpfS.equalsIgnoreCase("44444444444") || cpfS.equalsIgnoreCase("55555555555")
                || cpfS.equalsIgnoreCase("66666666666") || cpfS.equalsIgnoreCase("77777777777")
                || cpfS.equalsIgnoreCase("88888888888") || cpfS.equalsIgnoreCase("99999999999")){
                return false;
            }


            for (i = 0; i < cpf.length; i++) {
                cpf[i] = Integer.parseInt(cpfS.substring(i, i+1));
            }
            j = 0;
            for (i=10; i>1; i--) {
                soma += cpf[j]*i;
                j++;
            }
            aux = soma%11;
            if (aux < 2) {
                aux = 0;
                if (cpf[9] != aux) {
                    return false;
                }
            }
            else {
                aux = 11 - aux;
                if (cpf[9] != aux) {
                    return false;
                }
            }
            soma = 0;
            j = 0;
            for (i=11; i>1; i--) {
                soma += cpf[j]*i;
                j++;
            }
            aux = soma%11;
            if (aux < 2) {
                aux = 0;
                if (cpf[10] != aux) {
                    return false;
                }
            }
            else{
                aux = 11 - aux;
                if (cpf[10] != aux) {
                    return false;
                    
                }
            }
            return true;
        
        }while (true);
        
    }

}
