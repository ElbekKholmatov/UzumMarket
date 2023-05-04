package uz.market.uzum.domains.user;

import jakarta.persistence.*;
import lombok.*;
import uz.market.uzum.domains.Auditable;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(callSuper = true)
public class OTP extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OtpType otpType;
    @Column(nullable = false)
    private Long userID;

    @Builder(builderMethodName = "childBuilder")
    public OTP(Long createdBy, Long updateBy, LocalDateTime createdAt, LocalDateTime updatedAt, boolean isDeleted, Long id, String code, LocalDateTime expiresAt, OtpType otpType, Long userID) {
        super(createdBy, updateBy, createdAt, updatedAt, isDeleted);
        this.id = id;
        this.code = code;
        this.expiresAt = expiresAt;
        this.otpType = otpType;
        this.userID = userID;
    }

    public enum OtpType {
        PASSWORD_RESET, ACCOUNT_ACTIVATE
    }
}
