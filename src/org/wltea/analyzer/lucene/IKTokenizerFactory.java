package org.wltea.analyzer.lucene;

import java.io.Reader;
import java.util.Map;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.util.TokenizerFactory;
import org.apache.lucene.util.AttributeSource.AttributeFactory;

public class IKTokenizerFactory extends TokenizerFactory
{
  private boolean useSmart;

  public boolean useSmart()
  {
    return this.useSmart;
  }

  public void setUseSmart(boolean useSmart) {
    this.useSmart = useSmart;
  }

  public IKTokenizerFactory(Map<String, String> args) {
    super(args);
    assureMatchVersion();
    setUseSmart(getBoolean(args, "useSmart", true));
  }

  public Tokenizer create(AttributeFactory arg0, Reader input)
  {
    Tokenizer _IKTokenizer = new IKTokenizer(input, this.useSmart);
    return _IKTokenizer;
  }
}