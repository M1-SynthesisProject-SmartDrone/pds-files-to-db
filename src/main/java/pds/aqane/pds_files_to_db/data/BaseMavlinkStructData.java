package pds.aqane.pds_files_to_db.data;

import pds.aqane.pds_files_to_db.data.visitor.MavlinkStructVisitor;

/**
 * The sole purpose of this class is to have a common class between all mavlink
 * struct classes.
 *
 * @author Aldric Vitali Silvestre
 */
public abstract class BaseMavlinkStructData {

	private MavlinkStructs structName;
	
	private Long id;

	private long timestamp;

	public BaseMavlinkStructData(MavlinkStructs structName) {
		super();
		this.structName = structName;
	}

	public MavlinkStructs getStructName() {
		return structName;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Visitor pattern.
	 */
	public abstract <T> T accept(MavlinkStructVisitor<T> visitor);
}
