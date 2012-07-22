/**
 * libjinx2: a library for loading the NX file format
 * Copyright (C) 2012 Aaron Weiss <aaronweiss74@gmail.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package us.aaronweiss.libjinx2;

/**
 * An accessor for <code>INXNode</code>, wrapping a byte array.
 * @author Aaron Weiss
 * @version 1.0
 * @since 1.5
 */
public class NXNodeAccessor {
	protected SeekableLittleEndianAccessor slea;
	protected NXFile file;
	
	/**
	 * Creates a node accessor.
	 * @param data the data to access
	 * @param file the file to access from
	 */
	public NXNodeAccessor(byte[] data, NXFile file) {
		this.slea = new SeekableLittleEndianAccessor(data);
		this.file = file;
	}
	
	// TODO: implement.
}
