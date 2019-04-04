package tests;

import configs.Config;
import configs.ToLoggerPrintStream;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;

import static io.restassured.config.EncoderConfig.encoderConfig;


public class BaseTest {

    private static final String BASE_URI = Config.BASE_URI;
    private static final String BASE_PATH = Config.BASE_PATH;
    public static final String JSON_SCHEMA = "json_schema/order_schema.json";

    @BeforeSuite
    public void before() {

        Logger log = LoggerFactory.getLogger(this.getClass());
        ToLoggerPrintStream loggerPrintStream = new ToLoggerPrintStream(log);

        RestAssuredConfig config = RestAssured.config()
                .encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false).defaultContentCharset("UTF-8"))
                .logConfig(new LogConfig(loggerPrintStream.getPrintStream(), true));

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setConfig(config)
                .setBaseUri(BASE_URI)
                .setBasePath(BASE_PATH)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();
    }
}
