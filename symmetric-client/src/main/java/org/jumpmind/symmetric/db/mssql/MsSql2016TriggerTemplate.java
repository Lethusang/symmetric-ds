/**
 * Licensed to JumpMind Inc under one or more contributor
 * license agreements.  See the NOTICE file distributed
 * with this work for additional information regarding
 * copyright ownership.  JumpMind Inc licenses this file
 * to you under the GNU General Public License, version 3.0 (GPLv3)
 * (the "License"); you may not use this file except in compliance
 * with the License.
 *
 * You should have received a copy of the GNU General Public License,
 * version 3.0 (GPLv3) along with this library; if not, see
 * <http://www.gnu.org/licenses/>.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jumpmind.symmetric.db.mssql;

import org.jumpmind.symmetric.db.ISymmetricDialect;

public class MsSql2016TriggerTemplate extends MsSql2008TriggerTemplate {
    public MsSql2016TriggerTemplate(ISymmetricDialect symmetricDialect) {
        super(symmetricDialect);
        numberColumnTemplate = "case when $(tableAlias).\"$(columnName)\" is null then '' else ('\"' + convert(varchar(40), $(tableAlias).\"$(columnName)\", 3) + '\"') end";
        moneyColumnTemplate = "case when $(tableAlias).\"$(columnName)\" is null then '' else ('\"' + convert(varchar(40), $(tableAlias).\"$(columnName)\", 2) + '\"') end";
    }

    @Override
    protected String getCreateTriggerString() {
        if (symmetricDialect.getPlatform().getDatabaseInfo().isTriggersCreateOrReplaceSupported()) {
            return "create or alter trigger";
        }
        return super.getCreateTriggerString();
    }
}
