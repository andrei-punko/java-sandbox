package by.andd3dfx.digitalsignature;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class XmlDigitalSignatureUtilTest {

    private static final String FILE_TO_SIGN_NAME = "target/test-classes/file-to-sign.xml";
    private static final String SIGNED_FILE_NAME = "target/signed-file.xml";
    private XmlDigitalSignatureUtil xmlDigitalSignatureUtil;

    @Before
    public void setup() {
        xmlDigitalSignatureUtil = new XmlDigitalSignatureUtil();
    }

    @Test
    public void signXmlFileAndValidateSignature() throws Exception {
        xmlDigitalSignatureUtil.signXmlFile(FILE_TO_SIGN_NAME, SIGNED_FILE_NAME);

        assertTrue(xmlDigitalSignatureUtil.validateXmlSignature(SIGNED_FILE_NAME));
    }
}
