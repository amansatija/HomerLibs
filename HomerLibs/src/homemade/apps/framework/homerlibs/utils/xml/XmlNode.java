package homemade.apps.framework.homerlibs.utils.xml;

/**
 * XmlNode Is a container class made to supplement collecting data specifically from xml files
 * it has variables and functions that help ease the reading of data from xml files on adnroid platform 
 * 
 * 
 */
import java.util.ArrayList;
import java.util.HashMap;

public class XmlNode
	{
		String															name				= "";
		String															text				= "";
		HashMap<String, String>							attributes	= new HashMap<String, String>();
		HashMap<String, ArrayList<XmlNode>>	children		= new HashMap<String, ArrayList<XmlNode>>();
		
		/**
		 * This constructor resets/ initialises the node use this function if you
		 * wish to initialise or reset any node to an empty node. It does so by
		 * calling the resetNode function
		 * 
		 * @see XmlNode#resetNode()
		 */
		public XmlNode()
			{
				resetNode();
			}
		
		/**
		 * This is a Constructor that initialises a new instance of XmlNode with the
		 * given values
		 * 
		 * @param node_name
		 * @param node_text
		 * @param node_attributes_hashmap
		 * @param node_children_hashmap
		 */
		public XmlNode(String node_name, String node_text,
				HashMap<String, String> node_attributes_hashmap,
				HashMap<String, ArrayList<XmlNode>> node_children_hashmap)
			{
				name = node_name;
				text = node_text;
				attributes = node_attributes_hashmap;
				children = node_children_hashmap;
			}
		
		/**
		 * This function intialises all variables to an empty not null value
		 */
		public void resetNode()
			{
				name = "";
				text = "";
				attributes = new HashMap<String, String>();
				HashMap<String, ArrayList<XmlNode>> children = new HashMap<String, ArrayList<XmlNode>>();
			}
		
		/*************************************** Setter functions below *********************************************************/
		
		/**
		 * This functions helps sets the name of the node
		 * 
		 * @param node_name
		 */
		public void setName(String node_name)
			{
				name = node_name;
			}
		
		/**
		 * This function helps set the text of the node or in other words stores the
		 * value of the node
		 * 
		 * @param node_text
		 */
		public void setText(String node_text)
			{
				text = node_text;
			}
		
		/**
		 * This function helps store the node attributes and their values of the
		 * node
		 * 
		 * @param node_attributes_hashmap
		 */
		public void setAttributes(HashMap<String, String> node_attributes_hashmap)
			{
				attributes = node_attributes_hashmap;
			}
		
		/**
		 * This function helps set the children of the node to the children provided
		 * by the user
		 * 
		 * @param node_children_hashmap
		 */
		public void setChildren(
				HashMap<String, ArrayList<XmlNode>> node_children_hashmap)
			{
				children = node_children_hashmap;
			}
		
		/**
		 * This function helps add a given node to the list of children of the
		 * present node
		 * 
		 * @param child_node
		 */
		public void addChild(XmlNode child_node)
			{
				
				String child_name = child_node.getName().toLowerCase()
						.replaceAll(" ", "");
				
				if (!children.containsKey(child_name))
					{
						ArrayList<XmlNode> list_of_child_nodes_with_same_key = new ArrayList<XmlNode>();
						list_of_child_nodes_with_same_key.add(child_node);
						
						children.put(child_name, list_of_child_nodes_with_same_key);
					} else
					{
						ArrayList<XmlNode> list_of_child_nodes_with_same_key = new ArrayList<XmlNode>();
						list_of_child_nodes_with_same_key = children.get(child_name);
						list_of_child_nodes_with_same_key.add(child_node);
						children.put(child_name, list_of_child_nodes_with_same_key);
					}
			}
		
		/*************************************** Getter functions below *********************************************************/
		
		public String getName()
			{
				return name;
			}
		
		public String getText()
			{
				return text;
			}
		
		public HashMap<String, String> getAttributes()
			{
				return attributes;
			}
		
		public HashMap<String, ArrayList<XmlNode>> getChildren()
			{
				return children;
			}
		
	}
