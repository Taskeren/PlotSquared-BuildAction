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
 *                  Copyright (C) 2021 IntellectualSites
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
package com.plotsquared.core.synchronization;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LockRepositoryTest {

    private LockKey key;
    private LockRepository lockRepository;

    @BeforeEach
    void setUp() {
        this.key = LockKey.of("test");
        this.lockRepository = new LockRepository();
    }

    @Test
    @DisplayName("Unlock even if there is an error")
    void useLockUnlock() {
        Lock l = this.lockRepository.getLock(this.key);
        // Striped uses a ReentrantLock internally, and we need its isLocked method for this test
        if (!(l instanceof ReentrantLock lock)) {
            throw new IllegalStateException("Expected a ReentrantLock");
        }

        assertThrows(IllegalStateException.class, () -> {
            this.lockRepository.useLock(this.key, () -> {
                throw new IllegalStateException();
            });
        });
        assertFalse(lock.isLocked());
    }
}
