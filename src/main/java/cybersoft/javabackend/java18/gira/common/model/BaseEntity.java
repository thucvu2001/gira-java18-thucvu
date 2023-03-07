package cybersoft.javabackend.java18.gira.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import cybersoft.javabackend.java18.gira.common.util.DateTimeUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.experimental.UtilityClass;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor // constructor khong tham so
@SuperBuilder // (@Builder: de tao ra instance ma khong nhat thiet phai dien tat ca tham so, @SuperBuilder: de lop con có the ke thua va truy cap thuoc tinh cua lop cha)
@MappedSuperclass // de ke thua va trien khai thuoc tinh cua lop cha xuong DB
@EntityListeners(AuditingEntityListener.class) // kich hoat Jpa Auditor de tu dong điền cac thuoc tinh
// tu cai dat khi thuoc tinh thay doi (version, ...), cau hinh trong config/JpaConfiguration
public class BaseEntity implements Serializable { // implements de xu li du lieu de dang hon

    @Id // danh dau lam khoa chinh
    @Type(type = "uuid-char") // quy chinh kieu cho UUID la uuid-char (string), mac dinh la bytecode
    @GeneratedValue // tu dong sinh ra gia tri cho id
    @Column(name = Columns.ID) // dat ten cot trong DB
    protected UUID id; // su dung UUID lam khoa chinh

    @Version // tu dong sinh ra version
    @Column(name = Columns.VERSION)
    protected int version; // mỗi lần thay đổi thì sẽ có version mới

    @CreatedBy
    @Column(name = Columns.CREATED_BY)
    protected String createdBy; // tao boi ai?

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtils.DATETIME_FORMAT) // shape String
    @DateTimeFormat(pattern = DateTimeUtils.DATETIME_FORMAT)
    @CreatedDate
    @Column(name = Columns.CREATED_AT)
    protected LocalDateTime createdAt; // tao vao thời điểm nào?

    @LastModifiedBy
    @Column(name = Columns.LAST_MODIFIED_BY)
    private String lastModifiedBy; // nguoi thay doi du lieu cuoi cung?

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtils.DATETIME_FORMAT)
    @DateTimeFormat(pattern = DateTimeUtils.DATETIME_FORMAT)
    @LastModifiedDate
    @Column(name = Columns.LAST_MODIFIED_AT)
    private LocalDateTime lastModifiedAt; // thoi gian thay doi cuoi cung?

    @Override
    public boolean equals(Object obj) {
        return this.id.equals(BaseEntity.class.cast(obj).id);
    }

    @UtilityClass // lớp chức năng (khong tao instance) (inner class)
    static class Columns {
        static final String ID = "ID";
        static final String VERSION = "VERSION";
        static final String CREATED_BY = "CREATED_BY";
        static final String CREATED_AT = "CREATED_AT";
        static final String LAST_MODIFIED_BY = "LAST_MODIFIED_BY";
        static final String LAST_MODIFIED_AT = "LAST_MODIFIED_AT";
    }
}
