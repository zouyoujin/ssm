package com.ssm.common.pojo;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * DTO to customize the returned message
 *
 * @author lishen
 */
@XmlRootElement
public class RegistUserResult implements Serializable {

	private static final long serialVersionUID = -6416945762764740443L;
	
	private Long id;

    public RegistUserResult() {
    }

    public RegistUserResult(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
