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
 * An <code>NXNode</code> containing MP3 data.
 * @author Aaron Weiss
 * @version 1.0
 * @since 1.0
 */
public class NXMP3Node extends NXNode {
	private SeekableLittleEndianAccessor slea;
	private final long offset;
	
	/**
	 * Creates an <code>NXNode</code> for MP3 data.
	 * @param name the name of the node
	 * @param slea the accessor to laod the data from
	 * @param offset the offset to load at
	 * @param file the file of the node
	 * @param parent the parent of the node
	 */
	public NXMP3Node(String name, SeekableLittleEndianAccessor slea, long offset, NXFile file, INXNode parent) {
		super(name, null, file, parent);
		this.slea = slea;
		this.offset = offset;
	}

	@Override
	public byte[] getValue() throws NXException {
		if (offset == -1) {
			return null;
		} else if (value != null) {
			return (byte[]) value;
		} else {
			slea.seek(offset);
			long length = slea.getUInt();
			byte[] data = slea.getBytes((int) length);
			value = data;
			return data;
		}
	}
	
	@Override
	public NXNodeType getType() {
		return NXNodeType.MP3;
	}
}
