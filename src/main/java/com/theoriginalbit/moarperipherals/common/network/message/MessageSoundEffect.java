/**
 * Copyright 2014 Joshua Asbury (@theoriginalbit)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.theoriginalbit.moarperipherals.common.network.message;

/**
 * @author theoriginalbit
 * @since 13/10/2014
 */
@SuppressWarnings("unused")
public class MessageSoundEffect extends MessageGeneric {

    public MessageSoundEffect() {
        // required empty constructor
    }

    public MessageSoundEffect(double x, double y, double z, String name) {
        this(x, y, z, name, 1f);
    }

    public MessageSoundEffect(double x, double y, double z, String name, float volume) {
        this(x, y, z, name, volume, 1f);
    }

    public MessageSoundEffect(double x, double y, double z, String name, float volume, float pitch) {
        // make the packet for the clients
        stringData = new String[]{name};
        doubleData = new double[]{x, y, z};
        floatData = new float[]{volume, pitch};
    }

}
