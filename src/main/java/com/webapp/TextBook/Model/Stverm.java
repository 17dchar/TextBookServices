import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name="STVTERM")
public class Stevrm {
    @Id
    @Column(name = "STVTERM_CODE")
    @NotEmpty
    private String code;
    @Column(name = "STVTERM_DESC")
    @NotEmpty
    private String desc;