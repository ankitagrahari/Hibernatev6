package entity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

public abstract class DataObject implements Serializable, Cloneable {

    private static final long serialVersionUID = 1;

    private String id = null;
    private String name = null;
    private String hiddenBy = null;
    private Date createdDate = new Date();
    private Date modifiedDate = null;
    private Date hiddenDate = null;
    private String deleted = null;
    private boolean locked = false;
    private int hidden;

    public DataObject() {
        super();
    }

    public DataObject(String id) {
        super();
        this.id = id;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    
    public void setHidden(int hidden) {
        this.hidden = hidden;
    }
    
    public int getHidden() {
        return hidden;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getHiddenBy() {
        return hiddenBy;
    }
    
    public void setHiddenBy(String hiddenBy) {
        this.hiddenBy = hiddenBy;
    }
    
    public Date getHiddenDate() {
        return hiddenDate;
    }
    
    public void setHiddenDate(Date hiddenDate) {
        this.hiddenDate = hiddenDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * If this object has a parent, this method returns its id.  This gives
     * us a generic interface for getting a parent ID regardless of what
     * subclasses may call their parent id method.  This implementation
     * returns null.
     */
    public String getParentID() {
        return null;
    }

    /**
     * Returns true if the given id is a parent of this object.
     */
    public boolean isParent(String parentId) {
        return parentId == null ? false : parentId.equals(getParentID());
    }

    /**
     * Returns true if this object has not been saved to the database yet.
     * Returns false if it has been saved and contains a id value.
     */
    public boolean isNew() {
        return (this.id == null);
    }

    @Override
    public Object clone() {
        DataObject clone = null;
        try {
            clone = (DataObject) super.clone();
        } catch (CloneNotSupportedException e) {
            // Should not happen because Object supports cloning
            throw new RuntimeException("Clone failed", e);
        }

        if (createdDate != null) {
            clone.createdDate = (Date) createdDate.clone();
        }

        if (modifiedDate != null) {
            clone.modifiedDate = (Date) modifiedDate.clone();
        }

        return clone;
    }
    
    @Override
	public String toString() {
        return (getName() == null) ? "" : getName();
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public JSONObject toDescriptorJSON() throws JSONException {
        final JSONObject json = new JSONObject();
        json.putOpt("id", this.getId());
        json.putOpt("name", this.getName());
        return json;
    }

}