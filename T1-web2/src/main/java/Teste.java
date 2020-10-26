import java.time.LocalDate;
import java.time.LocalTime;

public class Teste {

    public static void calcularIdade(LocalDate dataNascimento){
        LocalDate dataDeHoje = LocalDate.now();
        int ano = dataDeHoje.getYear();
        if(dataDeHoje.getMonthValue() < dataNascimento.getMonthValue()){
            ano--;
        }else if(dataDeHoje.getMonthValue()== dataNascimento.getMonthValue() && dataDeHoje.getDayOfMonth()<dataNascimento.getDayOfMonth()){
            ano--;
        }
        int anoNascimento = dataNascimento.getYear();
        int idade = ano-anoNascimento;
        System.out.println(idade);
    }
    public static void main(String[] args) {
        LocalDate d = LocalDate.parse("1999-12-07");

        calcularIdade(d);
    }
}

