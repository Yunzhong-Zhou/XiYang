package com.xiyang.xiyang.model;

import java.io.Serializable;

/**
 * Created by Mr.Z on 2021/4/8.
 */
public class UpLoadTokenModel implements Serializable {
    /**
     * token : yczRETEozHT4ZfI2Fp_RpNPVzkTlV3QichQg_l0M:THl3z0ePAYdJxrcemSXSTmaf9qo=:eyJjYWxsYmFja1VybCI6bnVsbCwiY2FsbGJhY2tCb2R5Ijoie1wiZm5hbWVcIjpcIiQoZm5hbWUpXCIsXCJma2V5XCI6XCIkKGtleSlcIixcImRlc2NcIjpcIiQoeDpkZXNjKVwiLFwidWlkXCI6MTMsXCJpc19hZG1pblwiOnRydWUsXCJoYXNoXCI6XCIkKGV0YWcpXCIsXCJzaXplXCI6XCIkKGZzaXplKVwiLFwibmFtZVwiOlwiJCh4Om5hbWUpXCJ9Iiwic2NvcGUiOiJkZXZjbGVhbmVyIiwiZGVhZGxpbmUiOjE2MTc4NTQzNjJ9
     * key : store/d2e5f6b7e64f52b2232fd383469ddb35
     */

    private String token;
    private String key;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
