/*
 *       _____  _       _    _____                                _
 *      |  __ \| |     | |  / ____|                              | |
 *      | |__) | | ___ | |_| (___   __ _ _   _  __ _ _ __ ___  __| |
 *      |  ___/| |/ _ \| __|\___ \ / _` | | | |/ _` | '__/ _ \/ _` |
 *      | |    | | (_) | |_ ____) | (_| | |_| | (_| | | |  __/ (_| |
 *      |_|    |_|\___/ \__|_____/ \__, |\__,_|\__,_|_|  \___|\__,_|
 *                                    | |
 *                                    |_|
 *            PlotSquared plot management system for Minecraft
 *               Copyright (C) 2014 - 2022 IntellectualSites
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.plotsquared.core.plot.flag.implementations;

import com.plotsquared.core.configuration.caption.TranslatableCaption;
import com.plotsquared.core.plot.flag.types.StringFlag;
import org.checkerframework.checker.nullness.qual.NonNull;

public class GreetingFlag extends StringFlag<GreetingFlag> {

    public static final GreetingFlag GREETING_FLAG_EMPTY = new GreetingFlag("");

    protected GreetingFlag(@NonNull String value) {
        super(
                value,
                TranslatableCaption.of("flags.flag_category_string"),
                TranslatableCaption.of("flags.flag_description_greeting")
        );
    }

    @Override
    public GreetingFlag parse(@NonNull String input) {
        return flagOf(input);
    }

    @Override
    public GreetingFlag merge(@NonNull String newValue) {
        return flagOf(this.getValue() + " " + newValue);
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    @Override
    public String getExample() {
        return "&6Welcome to my plot!";
    }

    @Override
    protected GreetingFlag flagOf(@NonNull String value) {
        return new GreetingFlag(value);
    }

}
