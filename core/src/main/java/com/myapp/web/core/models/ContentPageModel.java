package com.myapp.web.core.models;

import com.adobe.cq.export.json.ExporterConstants;
import lombok.Getter;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;

import javax.annotation.PostConstruct;


@Model(
        adaptables = SlingHttpServletRequest.class,
        resourceType = { "myAppWeb/components/page" },
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(
        name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        selector = "content",
        extensions = ExporterConstants.SLING_MODEL_EXTENSION,
        options = { @ExporterOption(name = "SerializationFeature.WRITE_DATES_AS_TIMESTAMPS", value = "true") })
public class ContentPageModel {

    @Getter
    @ValueMapValue
    private String pageTitle;

    @Getter
    private MovieContentFragmentModel movie;

    @Self
    protected SlingHttpServletRequest request;

    @PostConstruct
    public void init(){
        Resource res = request.getResourceResolver().getResource("/content/dam/myAppWeb/cf/titanic");
        if(res != null){
            movie = res.adaptTo(MovieContentFragmentModel.class);
        }

    }

}
