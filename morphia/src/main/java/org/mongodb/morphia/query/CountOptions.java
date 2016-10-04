/*
 * Copyright 2016 MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mongodb.morphia.query;

/*
 * Copyright 2016 MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.client.model.Collation;
import com.mongodb.client.model.DBCollectionCountOptions;

import java.util.concurrent.TimeUnit;

import static com.mongodb.assertions.Assertions.isTrue;
import static com.mongodb.assertions.Assertions.notNull;

/**
 * The options for a count operation.
 *
 * @since 1.3
 * @mongodb.driver.manual reference/command/count/ Count
 */
public class CountOptions {
    private DBCollectionCountOptions options = new DBCollectionCountOptions();

    DBCollectionCountOptions getOptions() {
        return options;
    }

    /**
     * Gets the hint to apply.
     *
     * @return the hint, which should describe an existing
     */
    public String getHint() {
        return options.getHintString();
    }

    /**
     * Sets the hint to apply.
     *
     * @param hint the name of the index which should be used for the operation
     * @return this
     */
    public CountOptions hint(final String hint) {
        options.hintString(hint);
        return this;
    }


    /**
     * Gets the limit to apply.  The default is 0, which means there is no limit.
     *
     * @return the limit
     * @mongodb.driver.manual reference/method/cursor.limit/#cursor.limit Limit
     */
    public int getLimit() {
        return options.getLimit();
    }

    /**
     * Sets the limit to apply.
     *
     * @param limit the limit
     * @return this
     * @mongodb.driver.manual reference/method/cursor.limit/#cursor.limit Limit
     */
    public CountOptions limit(final int limit) {
        options.limit(limit);
        return this;
    }

    /**
     * Gets the number of documents to skip.  The default is 0.
     *
     * @return the number of documents to skip
     * @mongodb.driver.manual reference/method/cursor.skip/#cursor.skip Skip
     */
    public int getSkip() {
        return options.getSkip();
    }

    /**
     * Sets the number of documents to skip.
     *
     * @param skip the number of documents to skip
     * @return this
     * @mongodb.driver.manual reference/method/cursor.skip/#cursor.skip Skip
     */
    public CountOptions skip(final int skip) {
        options.skip(skip);
        return this;
    }

    /**
     * Sets the limit to apply.
     *
     * @param limit the limit
     * @return this
     * @mongodb.driver.manual reference/method/cursor.limit/#cursor.limit Limit
     */
    public CountOptions limit(final long limit) {
        isTrue("limit is too large", limit <= Integer.MAX_VALUE);
        return limit((int) limit);
    }

    /**
     * Sets the number of documents to skip.
     *
     * @param skip the number of documents to skip
     * @return this
     * @mongodb.driver.manual reference/method/cursor.skip/#cursor.skip Skip
     */
    public CountOptions skip(final long skip) {
        isTrue("skip is too large", skip <= Integer.MAX_VALUE);
        return skip((int) skip);
    }

    /**
     * Gets the maximum execution time on the server for this operation.  The default is 0, which places no limit on the execution time.
     *
     * @param timeUnit the time unit to return the result in
     * @return the maximum execution time in the given time unit
     */
    public long getMaxTime(final TimeUnit timeUnit) {
        notNull("timeUnit", timeUnit);
        return options.getMaxTime(timeUnit);
    }

    /**
     * Sets the maximum execution time on the server for this operation.
     *
     * @param maxTime  the max time
     * @param timeUnit the time unit, which may not be null
     * @return this
     */
    public CountOptions maxTime(final long maxTime, final TimeUnit timeUnit) {
        notNull("timeUnit", timeUnit);
        options.maxTime(maxTime, timeUnit);
        return this;
    }

    /**
     * Returns the readPreference
     *
     * @return the readPreference
     */
    public ReadPreference getReadPreference() {
        return options.getReadPreference();
    }

    /**
     * Sets the readPreference
     *
     * @param readPreference the readPreference
     * @return this
     */
    public CountOptions readPreference(final ReadPreference readPreference) {
        options.readPreference(readPreference);
        return this;
    }

    /**
     * Returns the readConcern
     *
     * @return the readConcern
     * @mongodb.server.release 3.2
     */
    public ReadConcern getReadConcern() {
        return options.getReadConcern();
    }

    /**
     * Sets the readConcern
     *
     * @param readConcern the readConcern
     * @return this
     * @mongodb.server.release 3.2
     */
    public CountOptions readConcern(final ReadConcern readConcern) {
        options.readConcern(readConcern);
        return this;
    }

    /**
     * Returns the collation options
     *
     * @return the collation options
     * @mongodb.server.release 3.4
     */
    public Collation getCollation() {
        return options.getCollation();
    }

    /**
     * Sets the collation
     *
     * @param collation the collation
     * @return this
     * @mongodb.server.release 3.4
     */
    public CountOptions collation(final Collation collation) {
        options.collation(collation);
        return this;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FindOptions)) {
            return false;
        }

        final CountOptions that = (CountOptions) o;

        return getLimit() == that.getLimit()
            && getMaxTime(TimeUnit.MILLISECONDS) == that.getMaxTime(TimeUnit.MILLISECONDS)
            && getSkip() == that.getSkip()
            && (getHint() == null && that.getHint() == null || getHint().equals(that.getHint()))
            && (getReadPreference() == null && that.getReadPreference() == null || getReadPreference().equals(that.getReadPreference()))
            && (getReadConcern() == null && that.getReadConcern() == null || getReadConcern().equals(that.getReadConcern()))
            && (getCollation() == null && that.getCollation() == null || getCollation().equals(that.getCollation()));

    }

    @Override
    public int hashCode() {
        return getOptions().hashCode();
    }
}
