package hu.flibielt.onbitlor.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ProgrammingStatisticId implements Serializable {

    private Long programming;

    private Long player;

    private Date date;
}
