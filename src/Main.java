import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        DecimalFormat format = new DecimalFormat("###,###.##");

        List<Funcionario> funcionarios = new ArrayList<>(listarFuncionarios());

        funcionarios.removeIf(f -> f.getNome().equals("João"));
        System.out.println("Listar funcionários: ");
        funcionarios.forEach(System.out::println);
        funcionarios.forEach(f -> f.aumentoDeSalario(0.10));

        Map<String, List<Funcionario>> funcionariosMap = funcionarios.stream()
                            .collect(Collectors.groupingBy(Funcionario::getFuncao));

        System.out.println("\n\nFuncionários agrupados por cargos: ");
        funcionariosMap.forEach((k,v) -> {
            System.out.println("Função de " + k + ":");
            v.forEach(System.out::println);
            System.out.println();
        });

        System.out.println("\nFuncionários que fazem aniversário no mês 10 ou 12: ");
        funcionarios.stream().filter( f -> f.getDataNascimento().getMonthValue() == 10 ||
                f.getDataNascimento().getMonthValue() ==12)
                .forEach(System.out::println);

        System.out.println("\nFuncionário com a maior idade: ");
        funcionarios.stream().reduce((f,a) -> f.getDataNascimento().isBefore(a.getDataNascimento()) ? f : a)
        .stream().forEach(f -> {
            int idade = LocalDate.now().compareTo(f.getDataNascimento());
            System.out.println("Nome: " + f.getNome() + "\nIdade: " + idade + " anos");
        });

        System.out.println("\nFuncionários em ordem alfabéticos: ");
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));
        funcionarios.forEach(System.out::println);

        System.out.println("\nTotal do salário dos funcionários: ");
        double totalSalary = funcionarios.stream()
                .collect(Collectors.summingDouble(f -> f.getSalario().doubleValue()));
        System.out.println("Total dos salários: R$ " + format.format(totalSalary));

        System.out.println("\nQuantos salários mínimos recebem cada um dos funcionários: ");
        funcionarios.forEach(f -> {
            double qtdSalarioMin = (f.getSalario().doubleValue() / 1212.00);
            System.out.printf(" %s possui %.1f salários mínimos!\n", f.getNome(), qtdSalarioMin );
        });

    }

    private static List<Funcionario> listarFuncionarios() {

        Funcionario f1 = new Funcionario("Maria",
                LocalDate.of(2000,10, 18),
                BigDecimal.valueOf(2009.44), "Operador");
        Funcionario f2 = new Funcionario("João",
                LocalDate.of(1990,5, 12),
                BigDecimal.valueOf(2284.38), "Operador");
        Funcionario f3 = new Funcionario("Caio",
                LocalDate.of(1961,5, 2),
                BigDecimal.valueOf(9836.14), "Coordenador");
        Funcionario f4 = new Funcionario("Miguel",
                LocalDate.of(1988,10, 14),
                BigDecimal.valueOf(19119.88), "Diretor");
        Funcionario f5 = new Funcionario("Alice",
                LocalDate.of(1995,1, 5),
                BigDecimal.valueOf(2223.68), "Recepcionista");
        Funcionario f6 = new Funcionario("Heitor",
                LocalDate.of(1999,11, 19),
                BigDecimal.valueOf(1582.72), "Operador");
        Funcionario f7 = new Funcionario("Arthur",
                LocalDate.of(1993,3, 31),
                BigDecimal.valueOf(4071.84), "Contador");
        Funcionario f8 = new Funcionario("Laura",
                LocalDate.of(1994,7, 8),
                BigDecimal.valueOf(3017.45), "Gerente");
        Funcionario f9 = new Funcionario("Heloísa",
                LocalDate.of(2003,5, 24),
                BigDecimal.valueOf(1606.85), "Eletricista");
        Funcionario f10 = new Funcionario("Helena",
                LocalDate.of(1996,9, 2),
                BigDecimal.valueOf(2799.93), "Gerente");
        return Arrays.asList(f1,f2,f3,f4,f5,f6,f7,f8,f9,f10);
    }

}
