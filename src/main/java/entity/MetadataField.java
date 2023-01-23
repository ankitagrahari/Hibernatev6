package entity;

import java.util.*;

public class MetadataField extends DataObject {
    private List<MetadataOption> options = new ArrayList<>();
    private String className;
    Integer fieldTypeNum;

    protected MetadataField() {
        super();
    }

    public String getClassName() {
        return className;
    }

    private void setClassName(String className) {
        this.className = className;
    }

    protected Integer getFieldTypeNum() {
        return 0;
    }

    protected void setFieldTypeNum(Integer fieldTypeNum) {
        this.fieldTypeNum = fieldTypeNum;
    }
    public void addOption(String newValue) {
        MetadataOption option = new MetadataOption(this, newValue);
        option.setSeqNum(options.size() + 1);
        options.add(option);
    }

    public List<MetadataOption> getOptions() {
        return options;
    }

    protected void setOptions(List<MetadataOption> options) {
        this.options = options;
        if (options == null) {
            return;
        }
        // Hibernate is loading null into index 0, "That don't make no sense!"
        for (Iterator<MetadataOption> i = options.iterator(); i.hasNext();) {
            if (i.next() == null) {
                i.remove();
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof MetadataField)) {
            return false;
        }

        MetadataField mf = (MetadataField) obj;
        return getId() == null ? false : getId().equals(mf.getId());
    }

    @Override
    public int hashCode() {
        return 29;
    }

    /**
     * Just for testing. Make sure we get a copy of the Options
     */
    public Object clone() {
        final MetadataField field = (MetadataField) super.clone();
        field.setOptions(new ArrayList<>(this.getOptions().size()));
        field.getOptions().addAll(this.getOptions());
        return field;
    }
}