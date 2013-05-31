/**
 *Main aim while creating  Xml Handler is to make a class that simplifies
 *fetching data from xmls..
 *
 *   Below is an example of a complex xml around which the class tries to generalize xml handling
 *   Let us first lay down basic terms....
 *   observe the xml example below
 * 
 * <?xml version="1.0" encoding="utf-8"?> 
<feed xmlns="http://www.w3.org/2005/Atom" xmlns:creativeCommons="http://backend.userland.com/creativeCommonsRssModule" ...">     
<title type="text">newest questions tagged android - Stack Overflow</title>
...
    <entry>
    ...
    </entry>
    <entry>
        <id>http://stackoverflow.com/q/9439999</id>
        <re:rank scheme="http://stackoverflow.com">0</re:rank>
        <title type="text">Where is my data file?</title>
        <category scheme="http://stackoverflow.com/feeds/tag?tagnames=android&sort=newest/tags" term="android"/>
        <category scheme="http://stackoverflow.com/feeds/tag?tagnames=android&sort=newest/tags" term="file"/>
        <author>
            <name>cliff2310</name>
            <uri>http://stackoverflow.com/users/1128925</uri>
        </author>
        <link rel="alternate" href="http://stackoverflow.com/questions/9439999/where-is-my-data-file" />
        <published>2012-02-25T00:30:54Z</published>
        <updated>2012-02-25T00:30:54Z</updated>
        <summary type="html">
            <p>I have an Application that requires a data file...</p>

        </summary>
    </entry>
    <entry>
    ...
    </entry>
...
</feed>
 * 
 * now from the above example every element that starts with "<" is a node 
 * so "feed"  "title"  "entry" "id" "re:rank" are all examples of nodes even 'name' and 'uri' 
 * also are nodes    
 * if you observe closely the node "title" has what is called as an attribute "type"
 * So "type" is an attribute of title . keep that in mind before trying to fetch the values.
 * 
 * also observe the node "re:rank" , it has a schema .this schema is nothing but  defination of
 * what is called as a namespace which in this case is "re" so from the node "re:rank" 
 * "re" is the namespace 
 * 
 * So basically we need to cater to fetching values from nodes and fetching values from 
 * attributes of these nodes and while doing that we have to 
 * look out for namespaces 
 * 
 * now that we have cleared that out let me begin with the class
 * 
 * 
 */

package homemade.apps.framework.homerlibs.utils.xml;

import homemade.apps.framework.homerlibs.utils.HomerLogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class XmlHelper {

	XmlPullParserFactory pull_factory;
	private XmlPullParser pull_parser;

	SAXParserFactory sax_factory = SAXParserFactory.newInstance();
	private SAXParser sax_parser;

	public static enum ParserType {
		PULLPARSER, SAXPARSER
	}

	Boolean initialised = false;

	ParserType curr_parser_type = ParserType.SAXPARSER;

	String XMLFilePath;
	InputStream is;

	/**
	 * This constructor initialises the xml hanlper class and its variables by
	 * calling the initialise() function
	 * 
	 * @see #initialise()
	 */

	public XmlHelper() {
		initialise();

	}

	/**
	 * This constructor when invoked first of all sets the current parser type
	 * to the desired parser type (which is the parameter passed to the function
	 * ) and then initialises the xml hanlper class and its variables by calling
	 * the initialise() function
	 * 
	 * @param desired_parser_type
	 *            :ParserType is an enum which helps xml handler to determine
	 *            the various xml parsers
	 * 
	 * @see #setParserType(ParserType)
	 * @see #initialise()
	 */
	public XmlHelper(ParserType desired_parser_type) {
		setParserType(desired_parser_type);// set parser function not only sets
											// the parser but also initialises
											// the the xml handler class by
											// default

	}

	/**
	 * This function basically initialises the sax or xml pull , parser and the
	 * parser factory that will be used by the class based on the current parser
	 * type variable.
	 * 
	 * i'e it switches between intialising either xml pull parser or sax parser
	 * based on the value of the curr_parser_type
	 */
	private void initialise() {

		switch (curr_parser_type) {
		case PULLPARSER:

			try {
				pull_factory = XmlPullParserFactory.newInstance();
				pull_parser = pull_factory.newPullParser();

			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}

			break;

		case SAXPARSER:

			try {
				sax_factory = SAXParserFactory.newInstance();

				sax_parser = sax_factory.newSAXParser();

			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}

			break;
		}
		initialised = true;
	}

	/**
	 * This function basically initialises the sax or xml pull , parser and the
	 * parser factory that will be used by the class based on the current parser
	 * type variable.
	 * 
	 * i'e it switches between intialising either xml pull parser or sax parser
	 * based on the value of the curr_parser_type
	 * 
	 * @param desired_parser_type
	 *            :ParserType is an enum which helps xml handler to determine
	 *            the various xml parsers
	 */
	private void initialise(ParserType desired_parser_type) {
		setParserType(desired_parser_type); // set parser function not only sets
											// the parser but also initialises
											// the the xml handler class

		initialised = true;
	}

	/**
	 * 
	 * This function hosts the handler for sax parsing which has been modified
	 * to handle XMl Nodes and thier children and returns a ArrayList of all the
	 * instances found for the searched node name
	 * 
	 * 
	 * 
	 * @param InputStream
	 *            xml quite self explanatory , first parameter as its name
	 *            suggests is the xml file that you wish to parse
	 * 
	 * @param String
	 *            node_name also quite self explanatory , second parameter as
	 *            its name suggests is name of the desired node you wish to
	 *            extract from the xml whoes location you passed in the first
	 *            parameter i.e String path_to_xml;
	 * 
	 * @return ArrayList<Node> The function returns a ArrayList of all the
	 *         instances found for the searched node name
	 * 
	 * @throws SAXException
	 * 
	 * @throws IOException
	 * 
	 */
	public ArrayList<XmlNode> getNode(InputStream xml_file, String node_name) {
		ArrayList<XmlNode> node_list = new ArrayList<XmlNode>();

		if (!initialised)
			setParserType(ParserType.SAXPARSER);// by default xml
												// handler sets
												// the parser to
												// sax parser

		switch (curr_parser_type) {
		case SAXPARSER:
			try {

				node_list = getNodeUsingSaxParser(xml_file, node_name);

			} catch (SAXException e1) {
				e1.printStackTrace();
				HomerLogger
						.d("XMLHANDLER",
								"Could not parse the document properly using saxparser");
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			break;

		case PULLPARSER:
			try {
				node_list = getNodeUsingPullParser(xml_file, node_name);

			} catch (XmlPullParserException e) {
				HomerLogger
						.d("XMLHANDLER",
								"Could not parse the document properly using pullparser");
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
		return node_list;
	}

	/**
	 * 
	 * This function hosts a 'for loop' which helps us pull events from the pull
	 * parser the 'for loop' has been modified to handle XMl Nodes and their
	 * children and returns a ArrayList of all the instances found for the
	 * searched node name
	 * 
	 * 
	 * This function is called by the getNode() function above
	 * 
	 * @param InputStream
	 *            xml quite self explanatory , first parameter as its name
	 *            suggests is the xml file that you wish to parse
	 * 
	 * @param String
	 *            node_name also quite self explanatory , second parameter as
	 *            its name suggests is name of the desired node you wish to
	 *            extract from the xml whoes location you passed in the first
	 *            parameter i.e String path_to_xml;
	 * 
	 * @return ArrayList<Node> The function returns a ArrayList of all the
	 *         instances found for the searched node name
	 * 
	 * @throws SAXException
	 * 
	 * @throws IOException
	 * 
	 */

	private ArrayList<XmlNode> getNodeUsingPullParser(InputStream xml_file,
			String node_name) throws XmlPullParserException, IOException {

		ArrayList<XmlNode> node_list = new ArrayList<XmlNode>();

		String current_node_name = "";

		String local_name = "";

		Boolean parsing_desired_node = false;

		HashMap<String, XmlNode> node_hashmap;

		HashMap<String, String> attributes_of_node_hashmap;

		XmlNode node = new XmlNode();
		XmlNode child_node = new XmlNode();
		XmlNode temp_node = new XmlNode();

		int prev_depth = 0;

		int curr_depth = 0;

		int max_depth = 0;

		int node_depth = 1;

		int curr_node_depth = 1;

		final String search_node_name = node_name.toLowerCase().replaceAll(" ",
				"");
		/**
		 * depth_array contains a list a nodes that are parent to the variable
		 * node .(i.e the list of current processing node's parents)
		 */
		ArrayList<XmlNode> depth_array = new ArrayList<XmlNode>();

		// HashMap<String, String> entryset = null;

		int eventType = 0;
		pull_parser.setInput(xml_file, null);
		eventType = pull_parser.getEventType();

		while (eventType != XmlPullParser.END_DOCUMENT) {

			if (eventType == XmlPullParser.START_TAG) {
				current_node_name = pull_parser.getName().toLowerCase()
						.replaceAll(" ", "");
				attributes_of_node_hashmap = new HashMap<String, String>();

				HomerLogger.d("xmlhandler",
						"value of search node name provided is ==:"
								+ search_node_name
								+ " and the value of current_node_name is :=="
								+ current_node_name);

				if (current_node_name.equals(search_node_name)
						&& !parsing_desired_node) {
					parsing_desired_node = true;
					node = new XmlNode();

					if (pull_parser.getAttributeCount() > 0) {
						int attr_size = pull_parser.getAttributeCount();
						for (int i = 0; i < attr_size; i++) {
							attributes_of_node_hashmap = new HashMap<String, String>();
							attributes_of_node_hashmap.put(
									pull_parser.getAttributeName(i),
									pull_parser.getAttributeValue(i));
						}
					}
					node.setName(pull_parser.getName());
					node.setAttributes(attributes_of_node_hashmap);
					node.setText(pull_parser.getText());
				}
				if (parsing_desired_node) {

					prev_depth = curr_depth;
					curr_depth++;

					if (curr_depth > node_depth + 1) {
						depth_array.add(node);
						node = new XmlNode();
						node = child_node;
						child_node = new XmlNode();
						node_depth = curr_depth - 1;
					}

					child_node = new XmlNode();
					if (pull_parser.getAttributeCount() > 0) {
						int attr_size = pull_parser.getAttributeCount();
						for (int i = 0; i < attr_size; i++) {
							attributes_of_node_hashmap = new HashMap<String, String>();
							attributes_of_node_hashmap.put(
									pull_parser.getAttributeName(i),
									pull_parser.getAttributeValue(i));
						}
					}
					child_node.setName(pull_parser.getName());
					child_node.setAttributes(attributes_of_node_hashmap);
					child_node.setText(pull_parser.getText());
				}
			}
			if (eventType == XmlPullParser.END_TAG) {
				if (parsing_desired_node) {
					curr_depth--;

					if (curr_depth < 1) {
						int depth_array_size = depth_array.size();
						if (depth_array_size == 0)
							node_list.add(node);
						else {
							HomerLogger.d("XMLHAndler",
									"is child of node logic gone wrong ");
						}
					} else {
						if (curr_depth == prev_depth) {
							node.addChild(child_node);
						}
						if (curr_depth < prev_depth) {
							if (depth_array.size() > 0) {
								int index = depth_array.size() - 1;
								temp_node = depth_array.get(index);
								depth_array.remove(index);
								temp_node.addChild(node);
								child_node = new XmlNode();
								node = new XmlNode();
								node = temp_node;
								temp_node = new XmlNode();
								node_depth--;
							}
						}
					}

				}
				if (pull_parser.getName().equalsIgnoreCase(search_node_name))
					parsing_desired_node = false;
			}
			eventType = pull_parser.next();
		}
		return node_list;
	}

	/**
	 * 
	 * This function hosts the handler for sax parsing which has been modified
	 * to handle XMl Nodes and thier children and returns a ArrayList of all the
	 * instances found for the searched node name
	 * 
	 * 
	 * This function is called by the getNodeSection() function above
	 * 
	 * @param InputStream
	 *            xml quite self explanatory , first parameter as its name
	 *            suggests is the xml file that you wish to parse
	 * 
	 * @param String
	 *            node_name also quite self explanatory , second parameter as
	 *            its name suggests is name of the desired node you wish to
	 *            extract from the xml whoes location you passed in the first
	 *            parameter i.e String path_to_xml;
	 * 
	 * @return ArrayList<Node> The function returns a ArrayList of all the
	 *         instances found for the searched node name
	 * 
	 * @throws SAXException
	 * 
	 * @throws IOException
	 * 
	 */
	private ArrayList<XmlNode> getNodeUsingSaxParser(InputStream xml_file,
			String node_name) throws SAXException, IOException {

		// XMLFilePath = xml_file;

		final String search_node_name = node_name.toLowerCase().replaceAll(" ",
				"");

		SAXHandler node_section_handler = saxHandlerForGetNodeFunction(search_node_name);

		parse(xml_file, node_section_handler);

		return node_section_handler.node_list;
	}

	private SAXHandler saxHandlerForGetNodeFunction(
			final String search_node_name) {
		SAXHandler node_section_handler = new SAXHandler() {
			String current_node_name = "";

			String local_name = "";

			Boolean parsing_desired_node = false;

			HashMap<String, XmlNode> node_hashmap;

			HashMap<String, String> attributes_of_node_hashmap;

			XmlNode node = new XmlNode();
			XmlNode child_node = new XmlNode();
			XmlNode temp_node = new XmlNode();
			Boolean patch = false;
			int prev_depth = 0;

			int curr_depth = 0;

			int max_depth = 0;

			int node_depth = 1;

			int curr_node_depth = 1;

			int patch_new_line = 0;

			String node_text = "";

			/**
			 * depth_array contains a list a nodes that are parent to the
			 * variable node .(i.e the list of current processing node's
			 * parents)
			 **/
			ArrayList<XmlNode> depth_array = new ArrayList<XmlNode>();

			@Override
			public void startDocument() throws SAXException {

				super.startDocument();
			}

			@Override
			public void startElement(String uri, String localName,
					String qName, Attributes attributes) throws SAXException {
				super.startElement(uri, localName, qName, attributes);

				current_node_name = localName.toLowerCase().replaceAll(" ", "");
				attributes_of_node_hashmap = new HashMap<String, String>();

				HomerLogger.d("xmlhandler",
						"value of search node name provided is ==:"
								+ search_node_name
								+ " and the value of current_node_name is :=="
								+ current_node_name);

				if (current_node_name.equals(search_node_name)
						&& !parsing_desired_node) {
					parsing_desired_node = true;
					node = new XmlNode();

					if (attributes.getLength() > 0) {
						int attr_size = attributes.getLength();
						for (int i = 0; i < attr_size; i++) {
							attributes_of_node_hashmap = new HashMap<String, String>();
							attributes_of_node_hashmap.put(
									attributes.getLocalName(i),
									attributes.getValue(i));
						}
					}
					node.setName(localName);
					node.setAttributes(attributes_of_node_hashmap);
				}
				if (parsing_desired_node) {

					prev_depth = curr_depth;
					curr_depth++;

					if (curr_depth > node_depth + 1) {
						depth_array.add(node);
						node = new XmlNode();
						node = child_node;
						child_node = new XmlNode();
						node_depth = curr_depth - 1;
					}

					child_node = new XmlNode();
					if (attributes.getLength() > 0) {
						int attr_size = attributes.getLength();
						for (int i = 0; i < attr_size; i++) {
							attributes_of_node_hashmap = new HashMap<String, String>();
							attributes_of_node_hashmap.put(
									attributes.getLocalName(i),
									attributes.getValue(i));
						}
					}
					child_node.setName(localName);
					child_node.setAttributes(attributes_of_node_hashmap);
				}
				patch = false;
				patch_new_line = 0;
			}

			public void characters(char[] ch, int start, int length)
					throws SAXException {
				patch_new_line++;

				if (!patch) {
					if (parsing_desired_node && patch_new_line == 1) {
						node_text = new String(ch, start, length);
						if (curr_depth == 1 && patch_new_line == 1)
							node.setText(node_text);
						else if (patch_new_line == 1)
							child_node.setText(node_text);

					} else if (parsing_desired_node && patch_new_line > 1) {
						String node_text_append = new String(ch, start, length);
						node_text = node_text + node_text_append;
						if (curr_depth == 1)
							node.setText(node_text);
						else
							child_node.setText(node_text);
					}
				}
			};

			@Override
			public void endElement(String uri, String localName, String qName)
					throws SAXException {
				super.endElement(uri, localName, qName);

				if (parsing_desired_node) {
					curr_depth--;

					if (curr_depth < 1) {
						int depth_array_size = depth_array.size();
						if (depth_array_size == 0)
							node_list.add(node);
						else {
							HomerLogger.d("XMLHAndler",
									"is child of node logic gone wrong ");
						}
					} else {
						if (curr_depth == prev_depth) {
							node.addChild(child_node);
						}
						if (curr_depth < prev_depth) {
							if (depth_array.size() > 0) {
								int index = depth_array.size() - 1;
								temp_node = depth_array.get(index);
								depth_array.remove(index);
								temp_node.addChild(node);
								child_node = new XmlNode();
								node = new XmlNode();
								node = temp_node;
								temp_node = new XmlNode();
								node_depth--;
							}
						}
					}

				}
				if (localName.equalsIgnoreCase(search_node_name))
					parsing_desired_node = false;
				patch = true;
			}

			@Override
			public void endDocument() throws SAXException {
				super.endDocument();
			}

		};
		return node_section_handler;
	}

	private void parse(InputStream xml_file, DefaultHandler handler)
			throws SAXException, IOException {
		sax_parser.parse(xml_file, handler);

	}

	/**
	 * setParserType is a setter function that sets the value of the variable
	 * curr_parser_type which is basically an enum that tells the xml helper
	 * class whether to use xmlpullparser or saxparser for fetching data from
	 * the xml
	 * 
	 * Also Note that By default it initialises the xmlhelper class and all its
	 * parsers if you don't whish to initialise the helper classes and just wish
	 * to set the current parser type then you have to pass a Boolean as an
	 * argument along with the desired_parser_type argument the value of this
	 * boolean should be 'false'
	 * 
	 * @param desired_parser_type
	 */
	public void setParserType(ParserType desired_parser_type) {
		curr_parser_type = desired_parser_type;

		initialise();
	}

	/**
	 * setParserType is a setter function that sets the value of the variable
	 * curr_parser_type which is basically an enum that tells the xml helper
	 * class whether to use xmlpullparser or saxparser for fetching data from
	 * the xml
	 * 
	 * Also Note that By default it initialises the xmlhelper class and all its
	 * parsers if you don't whish to initialise the helper classes and just wish
	 * to set the current parser type then you have to pass a Boolean as an
	 * argument along with the desired_parser_type argument the value of this
	 * boolean should be 'false'
	 * 
	 * @param desired_parser_type
	 *            : The parser type you want to set
	 * 
	 * @param should_initialise
	 *            : specify if you don't want the function to call initialise;
	 */
	public void setParserType(ParserType desired_parser_type,
			Boolean should_initialise) {
		curr_parser_type = desired_parser_type;

		if (should_initialise) {
			initialise();
		}
	}

	public XmlPullParser getPulParser() {

		return pull_parser;
	}

	public SAXParser getSaxParser() {

		return sax_parser;
	}

	public ParserType getParserType() {

		return curr_parser_type;
	}

	/**
	 * This functions takes file path and returns an inputstream for the file at
	 * that path
	 * 
	 * @throws FileNotFoundException
	 */
	public InputStream getInputStreamFor(String path_to_file)
			throws FileNotFoundException {
		InputStream is = null;
		if (new File(path_to_file).exists())
			is = new FileInputStream(new File(path_to_file));
		else
			HomerLogger.d("XmlHelper Error",
					"Could Not Find File At The Given Location :"
							+ path_to_file);
		return is;
	}

	public class SAXHandler extends DefaultHandler {
		public ArrayList<XmlNode> node_list = new ArrayList<XmlNode>();
	}
}

/***
 * A basic implementation of an application using this class to fetch data from
 * the xml is provided in the FrameworktestApp
 * 
 */

//
//
// private ArrayList<HashMap<String, XmlNode>> getNodeSectionUsingPullParser(
// String path_to_xml_file, String node_name)
// throws XmlPullParserException, IOException
// {
//
// ArrayList<HashMap<String, XmlNode>> list = new ArrayList<HashMap<String,
// XmlNode>>();
//
// HashMap<String, String> entryset = null;
//
// int eventType = 0;
//
// eventType = pull_parser.getEventType();
//
// int depth = -1;
//
// while (eventType != XmlPullParser.END_DOCUMENT)
// {
// int currdepth = pull_parser.getDepth();
//
// if (currdepth != depth) entryset = new HashMap<String, String>();
//
// if (eventType == XmlPullParser.START_TAG)
// {
// if (pull_parser.getName().toLowerCase().equals(node_name))
// {
// if (entryset != null)
// entryset
// .put(pull_parser.getName(), pull_parser.getText());
// if (depth == -1) depth = pull_parser.getDepth() + 1;
// }
// }
// if (eventType == XmlPullParser.END_TAG)
// {
// if (pull_parser.getName().toLowerCase().equals(node_name))
// {
//
// }
// }
// // list.add(entryset);
//
// eventType = pull_parser.next();
// }
// return list;
// }

// private void getAllValuesForNode(String node_name)
// {
//
// }
//
// private void getAttributeValueFor(String node_name, String attribute_name,
// String namespace )
// {
//
// }
//
// private ArrayList<HashMap<String,String>> parseXml() throws
// XmlPullParserException, IOException
// {
// ArrayList<HashMap<String,String>> list = new
// ArrayList<HashMap<String,String>>();
//
// HashMap<String,String> entryset;
//
// int eventType = pull_parser.getEventType();
//
// while (eventType != XmlPullParser.END_DOCUMENT)
// {
// entryset = new HashMap<String, String>();
//
// if (eventType == XmlPullParser.START_TAG)
// {
// entryset.put(pull_parser.getName(),pull_parser.getText());
// }
//
// list.add(entryset);
//
// eventType = pull_parser.next();
// }
// return list;
// }
//
// private ArrayList<HashMap<String,String>> parseXmlForNode(String node_name)
// throws XmlPullParserException, IOException
// {
// ArrayList<HashMap<String,String>> list = new
// ArrayList<HashMap<String,String>>();
//
// HashMap<String,String> entryset = null;
//
// int eventType = pull_parser.getEventType();
//
// int depth = -1;
//
// while (eventType != XmlPullParser.END_DOCUMENT)
// {
// int currdepth = pull_parser.getDepth();
//
// if(currdepth!=depth)
// entryset = new HashMap<String, String>();
//
// if (eventType == XmlPullParser.START_TAG)
// {
// if(pull_parser.getName().toLowerCase().equals(node_name))
// {
// if (entryset!=null)
// entryset.put(pull_parser.getName(),pull_parser.getText());
// if(depth==-1)
// depth = pull_parser.getDepth()+1;
// }
// }
// list.add(entryset);
//
// eventType = pull_parser.next();
// }
// return list;
// }
//
// public ArrayList<HashMap<String,String>>
// parseXmlForEntrySetViaSaxParser(String path_to_file)
// {
//
// try
// {
// sax_parser.parse(new File(XMLFilePath), this) ;
// } catch (SAXException e)
// {
// e.printStackTrace();
// } catch (IOException e)
// {
//
// e.printStackTrace();
// }
//
// return entry_sets_list;
// }

// /**
// // * constructor
// // *
// // */
// public XmlHandler(File xml)
// {
//
// }
//
// /**
// * constructor
// *
// */
// public XmlHandler(String path_to_xml)
// {
// initialise();
// }
//
// /**
// * constructor
// *
// */
// public XmlHandler(URL path_to_xml)
// {
// initialise();
// }
//
// /**
// * constructor
// *
// */