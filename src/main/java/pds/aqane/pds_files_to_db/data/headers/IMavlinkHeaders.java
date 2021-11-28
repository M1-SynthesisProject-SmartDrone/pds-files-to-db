package pds.aqane.pds_files_to_db.data.headers;

import pds.aqane.pds_files_to_db.data.converter.CheckConversionVisitor;

/**
 * Used to facilitate process while checking for valid conversion for example.
 * 
 * @author Aldric Vitali Silvestre
 * @see CheckConversionVisitor
 */
public interface IMavlinkHeaders {

	String getHeaderName();

}
