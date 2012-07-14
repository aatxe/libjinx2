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
 * The core exception of NX error handling.
 * @author Aaron Weiss
 * @author angelsl
 * @version 1.0
 */
public class NXException extends Exception {
	private static final long serialVersionUID = -8366147847191582495L;

	/**
	 * Creates an <code>NXException</code>.
	 * @param message the error message
	 */
	public NXException(String message) {
		super(message);
	}

	/**
	 * Creates an <code>NXException</code>.
	 * @param message the error message
	 * @param cause the reason behind the exception
	 */
	public NXException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Creates an <code>NXException</code>.
	 * @param cause the reason behind the exception
	 */
	public NXException(Throwable cause) {
		super(cause);
	}
}
