/**
 * THIS SOFTWARE IS LICENSED UNDER MIT LICENSE.<br>
 * <br>
 * Copyright 2017 Andras Berkes [andras.berkes@programmer.net]<br>
 * Based on Moleculer Framework for NodeJS [https://moleculer.services].
 * <br><br>
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:<br>
 * <br>
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.<br>
 * <br>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package services.moleculer.repl.commands;

import java.io.PrintWriter;
import java.net.URL;
import java.net.URLDecoder;

import services.moleculer.ServiceBroker;
import services.moleculer.repl.Command;
import services.moleculer.service.Name;

/**
 * Finds the specified class or resource. Sample:<br>
 * <br>
 * find java.lang.String
 */
@Name("find")
public class Find extends Command {

	@Override
	public String getDescription() {
		return "Find a class or resource";
	}

	@Override
	public String getUsage() {
		return "find <fullClassName>";
	}

	@Override
	public int getNumberOfRequiredParameters() {
		return 1;
	}

	@Override
	public void onCommand(ServiceBroker broker, PrintWriter out, String[] parameters) throws Exception {
		String name = parameters[0];
		int index = name.indexOf('.');
		if (index != -1) {
			name = name.replace('.', '/') + ".class";
		}
		URL url = getClass().getClassLoader().getResource(name);
		if (url == null) {
			url = getClass().getClassLoader().getResource(parameters[0]);
		}
		out.println("Location of resource:");
		out.println();
		out.print("  ");
		if (url == null) {
			out.println("Resource '" + parameters[0] + "' not found.");
		} else {
			out.println(URLDecoder.decode(url.toString(), "UTF8"));
		}
	}

}