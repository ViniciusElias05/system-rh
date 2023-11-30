import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Funcionario extends Pessoa{
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private BigDecimal salario;
    private String funcao;

    public Funcionario() {}

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getSalFormatado(){
        DecimalFormat format = new DecimalFormat("###,###.##");
        return format.format(salario);
    }


    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public void aumentoDeSalario(double porcentagem){
        double aumento = salario.doubleValue() + (salario.doubleValue() * porcentagem);
        salario = BigDecimal.valueOf(aumento);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Funcionario that)) return false;
        return Objects.equals(funcao, that.funcao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(funcao);
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "nome = " + getNome() +
                ", data de nascimento = " + getDataNascimento().format(fmt) +
                ", salario = " + getSalFormatado() +
                ", funcao = '" + funcao + '\'' +
                '}';
    }
}
