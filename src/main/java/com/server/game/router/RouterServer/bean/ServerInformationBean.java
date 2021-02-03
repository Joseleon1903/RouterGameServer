package com.server.game.router.RouterServer.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Created by jose de leon on 1/30/2021.
 */
@Data
@Component
public class ServerInformationBean {

    private String serverName;

    private String serverVersion;

    private String baseDomain;

    private String appName;

    private String appVersion;

    private String appRestBaseDomain;


}
