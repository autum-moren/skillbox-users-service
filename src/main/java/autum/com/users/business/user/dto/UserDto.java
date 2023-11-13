package autum.com.users.business.user.dto;

import autum.com.users.business.city.dto.CityDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

import static autum.com.users.utils.DateUtil.getLocalDateTimeUTCNow;
import static autum.com.users.utils.Generator.generateIdentifier;

@Data
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String identifier;
    private String firstName;
    private String lastName;
    private String middleName;
    private String msisdn;
    private String email;
    private CityDto city;
    private List<SkillDto> skills;
    private String avatarUrl;
    private String about;
    private LocalDateTime createdAt;
    private LocalDateTime deactivatedAt;
    private Sex sex;
    private Status status;
    private LocalDateTime birthdayAt;

    private Long subscriptions;
    private Long subscribers;

    public UserDto(CreateUserDto dto) {
        this.identifier = generateIdentifier();
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.middleName = dto.getMiddleName();
        this.msisdn = dto.getMsisdn();
        this.email = dto.getEmail();
        this.createdAt = getLocalDateTimeUTCNow();
        this.sex = dto.getSex();
        this.status = Status.ACTIVE;
        this.birthdayAt = dto.getBirthdayAt();
    }

    @Getter
    @Builder
    public static class SkillDto {
        private Long id;
        private String identifier;
        private String name;
        private Integer level;
    }

    public enum Sex {
        MALE,
        FEMALE,
        UNKNOWN;

        public static Sex fromInteger(int sex) {
            switch (sex) {
                case 1:
                    return MALE;
                case 2:
                    return FEMALE;
                case 3:
                    return UNKNOWN;
                default:
                    throw new IllegalArgumentException("Integer " + sex + "not processed!");
            }
        }

        public static int toInteger(Sex sex) {
            switch (sex) {
                case MALE:
                    return 1;
                case FEMALE:
                    return 2;
                case UNKNOWN:
                    return 3;
                default:
                    throw new IllegalArgumentException("Sex " + sex + "not processed!");
            }
        }

    }

    public enum Status {
        ACTIVE,
        BLOCKED,
        DEACTIVATED,
        DELETED;

        public static Status fromInteger(int status) {
            switch (status) {
                case 1:
                    return ACTIVE;
                case 2:
                    return BLOCKED;
                case 3:
                    return DEACTIVATED;
                case 4:
                    return DELETED;
                default:
                    throw new IllegalArgumentException("Integer " + status + "not processed!");
            }
        }

        public static int toInteger(Status status) {
            switch (status) {
                case ACTIVE:
                    return 1;
                case BLOCKED:
                    return 2;
                case DEACTIVATED:
                    return 3;
                case DELETED:
                    return 4;
                default:
                    throw new IllegalArgumentException("Status " + status + "not processed!");
            }
        }
    }
}