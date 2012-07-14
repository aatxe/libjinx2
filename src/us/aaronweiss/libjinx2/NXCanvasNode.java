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

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * An <code>NXNode</code> containing bitmap canvas data.
 * @author Aaron Weiss
 * @version 1.0
 * @since 1.0
 */
public class NXCanvasNode extends NXNode {
	private SeekableLittleEndianAccessor slea;
	private final long offset;

	/**
	 * Creates an <code>NXNode</code> for bitmap canvas data.
	 * @param name the name of the node
	 * @param slea the accessor for the node to gather data from
	 * @param offset the offset of the bitmap
	 * @param file the file of the node
	 * @param parent the parent node of this node
	 */
	public NXCanvasNode(String name, SeekableLittleEndianAccessor slea, long offset, NXFile file, INXNode parent) {
		super(name, null, file, parent);
		this.slea = slea;
		this.offset = offset;
	}

	@Override
	public BufferedImage getValue() throws NXException {
		if (offset == -1) {
			return null;
		} else if (this.value != null) {
			return (BufferedImage) this.value;
		} else {
			slea.seek(offset);
			int width = slea.getUShort();
			int height = slea.getUShort();
			long length = slea.getUInt();
			ByteBuffer output = ByteBuffer.allocateDirect(width * height * 4);
			NXCompressor.decompress(slea.getBuffer(), offset + 4, length + 4, output, 0);
			output.rewind();
			output.order(ByteOrder.LITTLE_ENDIAN);
			BufferedImage ret = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			for (int h = 0; h < height; h++) {
				for (int w = 0; w < width; w++) {
					int b = output.get() & 0xFF;
					int g = output.get() & 0xFF;
					int r = output.get() & 0xFF;
					int a = output.get() & 0xFF;
					ret.setRGB(w, h, (a << 24) | (r << 16) | (g << 8) | b);
				}
			}
			this.value = ret;
			return ret;
		}
	}
	
	@Override
	public NXNodeType getType() {
		return NXNodeType.Canvas;
	}
}
