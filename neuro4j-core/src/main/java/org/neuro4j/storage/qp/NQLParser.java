/* Generated By:JavaCC: Do not edit this line. NQLParser.java */
package org.neuro4j.storage.qp;
import java.lang.StringBuffer;
import java.io.StringReader;
import java.io.Reader;
import org.neuro4j.core.Network;
import org.neuro4j.core.Entity;
import org.neuro4j.core.Path;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

import org.neuro4j.storage.StorageException;
import org.neuro4j.storage.qp.NQLProcessor;

public class NQLParser implements NQLParserConstants {
  private NQLProcessor nqlProcessor;
  private String query;




  public NQLParser(String s, NQLProcessor nqlProcessor)
  {
    this ((Reader) (new StringReader(s)));
    this.nqlProcessor = nqlProcessor;
    this.query = s;
  }

/** 
 *  Top level
 */
  final public Network parse() throws ParseException, StorageException {
    command();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PIPE:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      jj_consume_token(PIPE);
      command();
    }
    jj_consume_token(0);
  ReInit((Reader) (new StringReader(this.query)));
  nqlProcessor.setParseCycle(nqlProcessor.SECOND_CYCLE);
    command();
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PIPE:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      jj_consume_token(PIPE);
      nqlProcessor.startNewCommand();
      command();
    }
    jj_consume_token(0);
    {if (true) return nqlProcessor.getNetwork();}
    throw new Error("Missing return statement in function");
  }

  final public void command() throws ParseException, StorageException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SELECT:
      jj_consume_token(SELECT);
      select();
      break;
    case INSERT:
      jj_consume_token(INSERT);
      insert();
      break;
    case UPDATE:
      jj_consume_token(UPDATE);
      update();
      break;
    case DELETE:
      jj_consume_token(DELETE);
      delete();
      break;
    case BEHAVE:
      jj_consume_token(BEHAVE);
      behave();
      break;
    case SQL:
      jj_consume_token(SQL);
      sql();
      break;
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return;}
  }

  final public void insert() throws ParseException, StorageException {
//  System.out.println(" insert ");  String ertype = "";
  Map<String, String> params = new HashMap<String, String>();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case E_LBRACE:
      jj_consume_token(E_LBRACE);
        ertype = "entity";
      break;
    case R_LBRACE:
      jj_consume_token(R_LBRACE);
        ertype = "relation";
      break;
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    getParams(params);
    jj_consume_token(RPAREN);
    nqlProcessor.insert(ertype, params);
    {if (true) return;}
  }

  final public void update() throws ParseException, StorageException {
//  System.out.println(" update ");  String ertype = "";
  Map<String, String> setProperties = new HashMap<String, String>();
  Set<String> removeProperties = new HashSet<String>();
  Network updateNet = null;
  Network addConnections = null;
  Network removeConnections = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SET_PROPERTY:
      jj_consume_token(SET_PROPERTY);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LIMIT:
      case STRING:
        getParams(setProperties);
        break;
      default:
        jj_la1[4] = jj_gen;
        ;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case E_LBRACE:
      case R_LBRACE:
      case F_LBRACE:
      case LBRACE:
            nqlProcessor.reset();
        parseER();
                addConnections = nqlProcessor.finishERParse();
        break;
      default:
        jj_la1[5] = jj_gen;
        ;
      }
      break;
    default:
      jj_la1[6] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case REMOVE_PROPERTY:
      jj_consume_token(REMOVE_PROPERTY);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LIMIT:
      case STRING:
        getList(removeProperties);
        break;
      default:
        jj_la1[7] = jj_gen;
        ;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case E_LBRACE:
      case R_LBRACE:
      case F_LBRACE:
      case LBRACE:
            nqlProcessor.reset();
        parseER();
            removeConnections = nqlProcessor.finishERParse();
        break;
      default:
        jj_la1[8] = jj_gen;
        ;
      }
      break;
    default:
      jj_la1[9] = jj_gen;
      ;
    }
    jj_consume_token(WHERE);
            nqlProcessor.reset();
    parseER();
        updateNet = nqlProcessor.finishERParse();
    nqlProcessor.update(updateNet, setProperties, removeProperties, addConnections, removeConnections);// update(ertype, params);
    {if (true) return;}
  }

  final public void behave() throws ParseException, StorageException {
//  System.out.println(" behave ");  Token tFlow, tComparator, tValue;
  String flow = "";
  String processor = "";
  Map<String, String> params = new HashMap<String, String>();
    jj_consume_token(LPAREN);
    getParams(params);
    jj_consume_token(RPAREN);
    nqlProcessor.behave(params);
    {if (true) return;}
  }

  final public void sql() throws ParseException, StorageException {
//  System.out.println(" sql ");  Token tFlow, tComparator, tValue;
  String flow = "";
  String processor = "";
  Map<String, String> params = new HashMap<String, String>();
    jj_consume_token(LPAREN);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LIMIT:
    case STRING:
      getParams(params);
      break;
    default:
      jj_la1[10] = jj_gen;
      ;
    }
    jj_consume_token(RPAREN);
    nqlProcessor.sql(params);
    {if (true) return;}
  }

/** * Reads parameters String like following:   a='b' c="d" * */
  final public void getParams(Map<String, String> params) throws ParseException {
//  System.out.println(" getParams() ");  Token tKey, tValue;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case STRING:
      tKey = jj_consume_token(STRING);
      break;
    case LIMIT:
      tKey = jj_consume_token(LIMIT);
      break;
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(EQUALS);
    tValue = jj_consume_token(QUOTED_STRING);
    String key = tKey.image;
    String value = tValue.image.substring(1, tValue.image.length() - 1);
    params.put(key.toLowerCase(), value);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LIMIT:
    case STRING:
      getParams(params);
      break;
    default:
      jj_la1[12] = jj_gen;
      ;
    }
    {if (true) return;}
  }

  final public void getList(Set<String> params) throws ParseException {
  Token tKey;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case STRING:
      tKey = jj_consume_token(STRING);
      break;
    case LIMIT:
      tKey = jj_consume_token(LIMIT);
      break;
    default:
      jj_la1[13] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    String key = tKey.image;
    params.add(key);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LIMIT:
    case STRING:
      getList(params);
      break;
    default:
      jj_la1[14] = jj_gen;
      ;
    }
    {if (true) return;}
  }

  final public void getParams4recursion(Map<String, String> params) throws ParseException, StorageException {
  Token tKey, tValue;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IGNORE:
      recursionIgnore();
      break;
    case USE_ONLY:
      recursionUseOnly();
      break;
    case LIMIT:
    case STRING:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case STRING:
        tKey = jj_consume_token(STRING);
        break;
      case LIMIT:
        tKey = jj_consume_token(LIMIT);
        break;
      default:
        jj_la1[15] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      jj_consume_token(EQUALS);
      tValue = jj_consume_token(QUOTED_STRING);
        String key = tKey.image;
        String value = tValue.image.substring(1, tValue.image.length() - 1);
        params.put(key.toLowerCase(), value);
      break;
    default:
      jj_la1[16] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IGNORE:
    case USE_ONLY:
    case LIMIT:
    case STRING:
      getParams4recursion(params);
      break;
    default:
      jj_la1[17] = jj_gen;
      ;
    }
    {if (true) return;}
  }

  final public void delete() throws ParseException, StorageException {
  System.out.println(" delete ");
    parseER();
      nqlProcessor.finishERParse();
    nqlProcessor.finishDelete();
    {if (true) return;}
  }

  final public void select() throws ParseException, StorageException {
    parseER();
            nqlProcessor.finishERParse();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case FILTER:
      filter();
      break;
    default:
      jj_la1[18] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case STRICT:
      strict();
      break;
    default:
      jj_la1[19] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LIMIT:
      limit();
      break;
    default:
      jj_la1[20] = jj_gen;
      ;
    }
    {if (true) return;}
  }

/** * Entiry point for * e[...] * r[...] * e[...]/r[...]/...  * */
  final public void parseER() throws ParseException, StorageException {
  //  System.out.println(" parseER ");  Network net = null;
  Map<String, String> techParams = new HashMap<String, String>();
  boolean optional = false;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case E_LBRACE:
    case R_LBRACE:
    case F_LBRACE:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case E_LBRACE:
        jj_consume_token(E_LBRACE);
                             nqlProcessor.startERAttributeProcessing("entity");
        break;
      case R_LBRACE:
        jj_consume_token(R_LBRACE);
                             nqlProcessor.startERAttributeProcessing("relation");
        break;
      case F_LBRACE:
        jj_consume_token(F_LBRACE);
                             nqlProcessor.startERAttributeProcessing("filter");
        break;
      default:
        jj_la1[21] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LPAREN:
      case CONNECTED:
      case STRING:
        net = parseERBraceExpression();
        break;
      default:
        jj_la1[22] = jj_gen;
        ;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LBRACE:
        jj_consume_token(LBRACE);
        getParams(techParams);
        jj_consume_token(RBRACE);
        break;
      default:
        jj_la1[23] = jj_gen;
        ;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case RBRACE:
        jj_consume_token(RBRACE);
        break;
      case RPAREN:
        jj_consume_token(RPAREN);
        break;
      default:
        jj_la1[24] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OPTIONAL:
        jj_consume_token(OPTIONAL);
                      optional = true;
        break;
      default:
        jj_la1[25] = jj_gen;
        ;
      }
                nqlProcessor.finishERAttributeProcessing(net, techParams, optional);
      break;
    case LBRACE:
      jj_consume_token(LBRACE);
      getParams4recursion(techParams);
      jj_consume_token(RBRACE);
                nqlProcessor.recursiveERSubpath(techParams);
      break;
    default:
      jj_la1[26] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SUBPATH:
      jj_consume_token(SUBPATH);
      nqlProcessor.startERSubpath();
      parseER();
      break;
    default:
      jj_la1[27] = jj_gen;
      ;
    }
    {if (true) return;}
  }

  final public void filter() throws ParseException, StorageException {
    jj_consume_token(FILTER);
    jj_consume_token(LPAREN);
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case E_LBRACE:
      case R_LBRACE:
        ;
        break;
      default:
        jj_la1[28] = jj_gen;
        break label_3;
      }
      getFilterExpression();
    }
    jj_consume_token(RPAREN);
        nqlProcessor.doFilter();
    {if (true) return;}
  }

  final public void getFilterExpression() throws ParseException, StorageException {
//  System.out.println(" getFilterExpression() ");  Token tFilterSize, tKey, tValue;
  String erType, sKey, sValue, filterSize;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case E_LBRACE:
      jj_consume_token(E_LBRACE);
        erType = "entity";
      break;
    case R_LBRACE:
      jj_consume_token(R_LBRACE);
        erType = "relation";
      break;
    default:
      jj_la1[29] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    tKey = jj_consume_token(STRING);

    jj_consume_token(EQUALS);
    //        tValue = < STRING >
    //        {
    //          sValue = tValue.image;
    //        }
    //      |
            tValue = jj_consume_token(QUOTED_STRING);
          // need to get rid of quotes.
          sValue = tValue.image.substring(1, tValue.image.length() - 1);
        sKey = tKey.image;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case RBRACE:
      jj_consume_token(RBRACE);
      break;
    case RPAREN:
      jj_consume_token(RPAREN);
      break;
    default:
      jj_la1[30] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case DIGIT:
      tFilterSize = jj_consume_token(DIGIT);
      break;
    case NUMBER:
      tFilterSize = jj_consume_token(NUMBER);
      break;
    default:
      jj_la1[31] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
      filterSize = tFilterSize.image;
      nqlProcessor.addFilter(erType, sKey, sValue, Integer.parseInt(filterSize));
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case COMMA:
      jj_consume_token(COMMA);
      getFilterExpression();
      break;
    default:
      jj_la1[32] = jj_gen;
      ;
    }
    {if (true) return;}
  }

/*void expand() throws StorageException :{  Token tExpandLevel;  int expandLevel = 1;}{  (< EXPAND >  {}  (    tExpandLevel = < DIGIT >  | tExpandLevel = < NUMBER >  )  {    expandLevel = Integer.parseInt(tExpandLevel.image);    nqlProcessor.setExpandLevel(expandLevel);  }  )  (    (      expandIgnore()    )  |    (      expandUseOnly()    )  )?  {    nqlProcessor.doExpand(expandLevel);    return;  }}*/
  final public void recursionIgnore() throws ParseException, StorageException {
    jj_consume_token(IGNORE);
    jj_consume_token(LPAREN);
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DIGIT:
      case NUMBER:
      case STRING:
        ;
        break;
      default:
        jj_la1[33] = jj_gen;
        break label_4;
      }
      getIgnoreAttribute();
    }
    jj_consume_token(RPAREN);

    {if (true) return;}
  }

  final public void recursionUseOnly() throws ParseException, StorageException {
    jj_consume_token(USE_ONLY);
    jj_consume_token(LPAREN);
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DIGIT:
      case NUMBER:
      case STRING:
        ;
        break;
      default:
        jj_la1[34] = jj_gen;
        break label_5;
      }
      getUseOnlyAttribute();
    }
    jj_consume_token(RPAREN);

    {if (true) return;}
  }

  final public void getUseOnlyAttribute() throws ParseException, StorageException {
  Token tUseOnlyDepthLevel = null;
  int useOnlyDepthLevel = -1;
  Token tKey, tComparator, tValue;
  String sKey, sValue;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case DIGIT:
    case NUMBER:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DIGIT:
        tUseOnlyDepthLevel = jj_consume_token(DIGIT);
        break;
      case NUMBER:
        tUseOnlyDepthLevel = jj_consume_token(NUMBER);
        break;
      default:
        jj_la1[35] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[36] = jj_gen;
      ;
    }
    if (null != tUseOnlyDepthLevel)
        useOnlyDepthLevel = Integer.parseInt(tUseOnlyDepthLevel.image);
    tKey = jj_consume_token(STRING);

    tComparator = jj_consume_token(EQUALS);
    tValue = jj_consume_token(QUOTED_STRING);
      // need to get rid of quotes.
      sValue = tValue.image.substring(1, tValue.image.length() - 1);
    sKey = tKey.image;
    nqlProcessor.addExpandUseOnlyAttribute(useOnlyDepthLevel, sKey, sValue);

    {if (true) return;}
  }

  final public void getIgnoreAttribute() throws ParseException, StorageException {
  Token tIgnoreDepthLevel = null;
  int ignoreDepthLevel = -1;
  Token tKey, tComparator, tValue;
  String sKey, sValue;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case DIGIT:
    case NUMBER:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DIGIT:
        tIgnoreDepthLevel = jj_consume_token(DIGIT);
        break;
      case NUMBER:
        tIgnoreDepthLevel = jj_consume_token(NUMBER);
        break;
      default:
        jj_la1[37] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[38] = jj_gen;
      ;
    }
    if (null != tIgnoreDepthLevel)
        ignoreDepthLevel = Integer.parseInt(tIgnoreDepthLevel.image);
    tKey = jj_consume_token(STRING);

    tComparator = jj_consume_token(EQUALS);
    tValue = jj_consume_token(QUOTED_STRING);
      // need to get rid of quotes.
      sValue = tValue.image.substring(1, tValue.image.length() - 1);
    sKey = tKey.image;
    nqlProcessor.addExpandIgnoreAttribute(ignoreDepthLevel, sKey, sValue);

    {if (true) return;}
  }

  final public Network parseERAttribute() throws ParseException, StorageException {
  Token tKey, tComparator, tValue;
  String sKey, sValue;
  Network net;
    tKey = jj_consume_token(STRING);

    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EQUALS:
      tComparator = jj_consume_token(EQUALS);
      break;
    case NOTEQUAL:
      tComparator = jj_consume_token(NOTEQUAL);
      break;
    case LIKE:
      tComparator = jj_consume_token(LIKE);
      break;
    default:
      jj_la1[39] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    tValue = jj_consume_token(QUOTED_STRING);
      // need to get rid of quotes.
      sValue = tValue.image.substring(1, tValue.image.length() - 1);
    sKey = tKey.image;
//    System.out.println(sKey + " = " + sValue);
    Map params = new HashMap();
    params.put("key", sKey);
    params.put("comparator", tComparator.image);
    params.put("value", sValue);
    Network net1 = nqlProcessor.addERAttribute(params);

    {if (true) return net1;}
    throw new Error("Missing return statement in function");
  }

// (a='1' AND b='2' AND c='3')  final public Network parseERSimpleExpression(Network inputNet) throws ParseException, StorageException {
  Network net1 = inputNet;
  if (null == net1)
  {
    net1 = new Network();
  }
  Network net2 = null;
  String operand = null;
    operand = attributeExpressionOperand();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LPAREN:
      jj_consume_token(LPAREN);
                    // Map params = new HashMap();
                    // Network net =
                    // don't use return parameter - because it isn't used in InMemoryProcessor. In SolrProcessor is used for brace generation 
                // nqlProcessor.startERAttributeExpression(new HashMap());
                nqlProcessor.startERAttributeExpression(null);
      net2 = parseERBraceExpression();
      jj_consume_token(RPAREN);
            // Map params = new HashMap();
            // Network net =
            // don't use return parameter - because it isn't used in InMemoryProcessor. In SolrProcessor is used for brace generation 
            // nqlProcessor.finishERAttributeExpression(new HashMap());
            nqlProcessor.finishERAttributeExpression(null);
      break;
    case STRING:
      net2 = parseERAttribute();
      break;
    case CONNECTED:
            Set<Path> connectedStack = nqlProcessor.getDefaultConnectedStack();
      net2 = parseConnected(connectedStack);
      break;
    default:
      jj_la1[40] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    Map params = new HashMap();
    params.put("net1", net1);
    params.put("net2", net2);
    params.put("operand", operand);
    Network net = nqlProcessor.doSimpleERAttributeExpression(params);

    {if (true) return net;}
    throw new Error("Missing return statement in function");
  }

  final public Network parseConnectedAttribute(Set<Path> connectedStack) throws ParseException, StorageException {
  Token tKey, tComparator, tValue;
  String sKey, sValue;
    tKey = jj_consume_token(STRING);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case EQUALS:
      tComparator = jj_consume_token(EQUALS);
      break;
    case NOTEQUAL:
      tComparator = jj_consume_token(NOTEQUAL);
      break;
    case LIKE:
      tComparator = jj_consume_token(LIKE);
      break;
    default:
      jj_la1[41] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    tValue = jj_consume_token(QUOTED_STRING);
      // need to get rid of quotes.
      sValue = tValue.image.substring(1, tValue.image.length() - 1);
    sKey = tKey.image;
    Map params = new HashMap();
    params.put("key", sKey);
    params.put("comparator", tComparator.image);
    params.put("value", sValue);

    Network net = nqlProcessor.getConnected(connectedStack, params);
    {if (true) return net;}
    throw new Error("Missing return statement in function");
  }

  final public Network parseConnectedBraceExpression(Set<Path> connectedStack) throws ParseException, StorageException {
  Network net = new Network();
  boolean isEmpty = true;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LPAREN:
    case STRING:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LPAREN:
        jj_consume_token(LPAREN);
        net = parseConnectedBraceExpression(connectedStack);
        jj_consume_token(RPAREN);
        break;
      case STRING:
        net = parseConnectedAttribute(connectedStack);
      isEmpty = false;
      System.out.println(" parseConnectedBraceExpression() - > not empty ");
        break;
      default:
        jj_la1[42] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[43] = jj_gen;
      ;
    }
    if (isEmpty)
    {
      // e.g. select e[connected()]  - > should return entities which have relations ?
    }
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case AND:
      case OR:
        ;
        break;
      default:
        jj_la1[44] = jj_gen;
        break label_6;
      }
      // (a='1' AND b='2' AND c='3')    
          net = parseConnectedSimpleExpression(connectedStack, net);
    }
    {if (true) return net;}
    throw new Error("Missing return statement in function");
  }

  final public Network parseConnectedSimpleExpression(Set<Path> connectedStack, Network inputNet) throws ParseException, StorageException {
  Network net1 = inputNet;
  if (null == net1)
  {
    net1 = new Network();
  }
  Network net2 = null;
  String operand = null;
    operand = attributeExpressionOperand();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LPAREN:
      jj_consume_token(LPAREN);
      net2 = parseConnectedBraceExpression(connectedStack);
      jj_consume_token(RPAREN);
      break;
    case STRING:
      net2 = parseConnectedAttribute(connectedStack);
      break;
    case CONNECTED:
            nqlProcessor.updateConnectedStack(connectedStack, net1);
      net2 = parseConnected(connectedStack);
      break;
    default:
      jj_la1[45] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    Map params = new HashMap();
    params.put("net1", net1);
    params.put("net2", net2);
    params.put("operand", operand);
    Network net = nqlProcessor.doSimpleERAttributeExpression(params);

    {if (true) return net;}
    throw new Error("Missing return statement in function");
  }

  final public Network parseConnected(Set<Path> connectedStack) throws ParseException, StorageException {
  Network connectedNet = null;
    jj_consume_token(CONNECTED);
    jj_consume_token(LPAREN);
    connectedNet = parseConnectedBraceExpression(connectedStack);
    jj_consume_token(RPAREN);
    Network net = nqlProcessor.finishGetConnected(connectedStack, connectedNet);
    {if (true) return net;}
    throw new Error("Missing return statement in function");
  }

// AND | OR  final public String attributeExpressionOperand() throws ParseException, StorageException {
  String operand = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case AND:
      jj_consume_token(AND);
        nqlProcessor.addERAttributeExpression("AND");
        operand = "AND";
      break;
    case OR:
      jj_consume_token(OR);
        nqlProcessor.addERAttributeExpression("OR");
        operand = "OR";
      break;
    default:
      jj_la1[46] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return operand;}
    throw new Error("Missing return statement in function");
  }

  final public Network parseERBraceExpression() throws ParseException, StorageException {
  Network net1 = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LPAREN:
      jj_consume_token(LPAREN);
            // Map params = new HashMap();
            // Network net =
            // don't use return parameter - because it isn't used in InMemoryProcessor. In SolrProcessor is used for brace generation 
            // nqlProcessor.startERAttributeExpression(new HashMap());
            nqlProcessor.startERAttributeExpression(null);
      net1 = parseERBraceExpression();
      jj_consume_token(RPAREN);
                // Map params = new HashMap();
            // Network net =
            // don't use return parameter - because it isn't used in InMemoryProcessor. In SolrProcessor is used for brace generation 
            // nqlProcessor.finishERAttributeExpression(new HashMap());
            nqlProcessor.finishERAttributeExpression(null);
      break;
    case STRING:
      net1 = parseERAttribute();
      break;
    case CONNECTED:
            Set<Path> connectedStack = nqlProcessor.getDefaultConnectedStack();
      net1 = parseConnected(connectedStack);
      break;
    default:
      jj_la1[47] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case AND:
      case OR:
        ;
        break;
      default:
        jj_la1[48] = jj_gen;
        break label_7;
      }
      // (a='1' AND b='2' AND c='3')    
          net1 = parseERSimpleExpression(net1);
    }
    {if (true) return net1;}
    throw new Error("Missing return statement in function");
  }

  final public void strict() throws ParseException, StorageException {
    jj_consume_token(STRICT);
        nqlProcessor.setStrict(true);
    {if (true) return;}
  }

  final public void limit() throws ParseException, StorageException {
//  System.out.println(" strict() ");  Token tMaxNetworkSize;
    jj_consume_token(LIMIT);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case DIGIT:
      tMaxNetworkSize = jj_consume_token(DIGIT);
      break;
    case NUMBER:
      tMaxNetworkSize = jj_consume_token(NUMBER);
      break;
    default:
      jj_la1[49] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
        nqlProcessor.setMaxOutputNetworkSize(Long.parseLong(tMaxNetworkSize.image));
    {if (true) return;}
  }

  /** Generated Token Manager. */
  public NQLParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[50];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x100,0x100,0x47c00,0x18000000,0x400000,0x78000000,0x8000,0x400000,0x78000000,0x10000,0x400000,0x400000,0x400000,0x400000,0x400000,0x400000,0x580000,0x580000,0x200000,0x800000,0x400000,0x38000000,0x0,0x40000000,0x80000000,0x4000000,0x78000000,0x0,0x18000000,0x18000000,0x80000000,0xc0,0x0,0xc0,0xc0,0xc0,0xc0,0xc0,0xc0,0x0,0x0,0x0,0x0,0x0,0x200,0x0,0x200,0x0,0x200,0xc0,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x0,0x200,0x0,0x0,0x200,0x0,0x0,0x200,0x200,0x200,0x200,0x200,0x200,0x200,0x200,0x0,0x0,0x0,0x0,0x304,0x0,0x8,0x0,0x0,0x1,0x0,0x0,0x8,0x0,0x80,0x200,0x200,0x0,0x0,0x0,0x0,0x70,0x304,0x70,0x204,0x204,0x2,0x304,0x2,0x304,0x2,0x0,};
   }

  /** Constructor with InputStream. */
  public NQLParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public NQLParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new NQLParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 50; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 50; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public NQLParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new NQLParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 50; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 50; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public NQLParser(NQLParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 50; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(NQLParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 50; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[43];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 50; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 43; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
