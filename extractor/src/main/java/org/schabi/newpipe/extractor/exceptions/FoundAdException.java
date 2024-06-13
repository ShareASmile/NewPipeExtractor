package org.schabi.newpipe.extractor.exceptions;

/*
 * Created by Christian Schabesberger on 12.09.16.
 *
 * Copyright (C) 2016 Christian Schabesberger <chris.schabesberger@mailbox.org>
 * FoundAdException.java is part of NewPipe.
 *
 * NewPipe Extractor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NewPipe Extractor is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with NewPipe Extractor.  If not, see <http://www.gnu.org/licenses/>.
 */

public class FoundAdException extends ParsingException {
    public FoundAdException(final String message) {
        super(message);
    }

    public FoundAdException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
