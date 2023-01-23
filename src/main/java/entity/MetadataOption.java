package entity;

import java.io.Serializable;
import java.util.Date;

public class MetadataOption implements Serializable {

    private static final long serialVersionUID = 1;

    private String id;
    private String value;
    private Date createdDate = new Date();
    private MetadataField field;
    private int seqNum;

    protected int getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(int seqNum) {
        this.seqNum = seqNum;
    }

    protected MetadataOption() {
        super();
    }

    public MetadataOption(MetadataField field, String value) {
        this.field = field;
        this.value = value;
        this.createdDate = new Date();
    }

    /**
     * Needs to be public when delete MetadataValue from the set by
     * giving MetadataOption.
     */
    public MetadataField getField() {
        return field;
    }

    protected void setField(MetadataField field) {
        this.field = field;
    }

    protected Date getCreatedDate() {
        return createdDate;
    }

    protected void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    protected String getValue() {
        return value;
    }

    protected void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value == null ? "" : value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof MetadataOption)) {
            return false;
        }

        MetadataOption mv = (MetadataOption) obj;
        return getId() == null ? false : getId().equals(mv.getId());
    }

    @Override
    public int hashCode() {
        return 19;
    }

}