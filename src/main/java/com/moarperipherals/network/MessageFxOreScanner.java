/**
 * Copyright 2014-2015 Joshua Asbury (@theoriginalbit)
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.moarperipherals.network;

/**
 * @author theoriginalbit
 * @since 15/11/14
 */
@SuppressWarnings("unused")
public class MessageFxOreScanner extends MessageGeneric {

    public MessageFxOreScanner() {
        // required empty constructor
    }

    public MessageFxOreScanner(int dimensionId, double xPos, double yPos, double zPos) {
        intData = new int[]{dimensionId};
        doubleData = new double[]{xPos, yPos, zPos};
    }

}
