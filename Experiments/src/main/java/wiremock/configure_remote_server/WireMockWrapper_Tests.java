/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* WireMockWrapper_Tests.java class
*
* @name    : WireMockWrapper_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Mar 10, 2021
****************************************************************************/

package wiremock.configure_remote_server;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import org.apache.http.HttpHeaders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


final class WireMockWrapper implements AutoCloseable
{
	private static void Sleep(final long milliseconds)
	{
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException ignored) {}
	}

	private final static class WireMockWrapper_Tests
	{
		public static void main(String[] args) throws IOException {
			AddStub_Request_Delete();
		}

		public static void AddStub_Request_Delete() throws IOException {
			WireMockWrapper wrapper = new WireMockWrapper();
			wrapper.stubFor(WireMock.get(WireMock.urlEqualTo("/context"))
					.willReturn(WireMock.aResponse()
							.withHeader(HttpHeaders.CONTENT_TYPE, "text/plain")
							.withStatus(200)
							.withBody("<html><body bgcolor='gray'>1111222223333</body></html>")));

			new WireMockClient__Apache.ApacheWireMockClient().GET_Request("/context");
			Sleep(5000);

			wrapper.removeMappings();
		}
	}

	private final static WireMock wireMock = new WireMock();
	private final static List<StubMapping> mappings = new ArrayList<StubMapping>();
	
	static {
		WireMock.configureFor("127.0.0.1", 8085);
	}
	
	public static StubMapping stubFor(MappingBuilder mappingBuilder)
	{
		StubMapping mapping = WireMock.givenThat(mappingBuilder);
		mappings.add(mapping);
		return mapping;
	}
	
	public void removeMappings() {
		for (final StubMapping stub: mappings) {
			WireMock.removeStub(stub);
		}
	}

	@Override
	public void close() throws Exception {
		removeMappings();
	}
}