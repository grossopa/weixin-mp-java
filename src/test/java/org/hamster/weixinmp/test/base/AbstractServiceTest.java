package org.hamster.weixinmp.test.base;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * @author grossopaforever@gmail.com
 * @version Mar 8, 2013 3:51:14 PM Change set
 */
@ContextConfiguration(locations = { "file:src/test/resources/applicationContext-test-weixinmp.xml"})
public abstract class AbstractServiceTest extends AbstractJUnit4SpringContextTests {


}
