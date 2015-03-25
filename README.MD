## Introduction
基于IK Analyzer分词器的Solr插件

```xml
<analyzer>
	<tokenizer class="org.wltea.analyzer.lucene.IKTokenizerFactory" useSmart="false"/>
</analyzer>
```

 *useSmart*参数 － 分词模式 , IK支持 细粒度切分 和 智能切分, 设置为true使用智能切分，false为细粒度切分。在[sunspot](http://sunspot.github.io/)中实际使用下来，将index和query的 *useSmart* 设置为false，即使用细粒度切分模式效果最佳，具体视需求而定，详细可参见IK手册。
  
Solr提供一些filter对英文进行分词，原IK分词器会将英文大写转化成小写，tokenizer会先于filter致使filter无效，已在源码中修正，使用filter进行一些配置可以对英文进行词干提取和驼峰分词，如LoveLive会被分成love live 

```xml
<tokenizer class="org.wltea.analyzer.lucene.IKTokenizerFactory" useSmart="false"/>
<filter class="solr.WordDelimiterFilterFactory"
    protected="protwords.txt"
    generateWordParts="1"
    generateNumberParts="1"
    catenateWords="1"
    catenateNumbers="1"
    catenateAll="0"
    splitOnCaseChange="1"
    preserveOriginal="1"/>
<filter class="solr.LengthFilterFactory" min="2" max="50" />
<filter class="solr.LowerCaseFilterFactory"/>
<filter class="solr.SnowballPorterFilterFactory" language="English" protected="protwords.txt"/>
<filter class="solr.RemoveDuplicatesTokenFilterFactory"/>

```
## Tips
现有的分词算法可分为三大类：基于字符串匹配的分词方法、基于理解的分词方法和基于统计的分词方法。**IK是基于字符串匹配的分词方法，其所使用的词库将直接影响分词的效果。**在使用中可针对搜索对应的相关领域，定制相关度较高的词库，替换掉默认词库，以取得更好的分词效果。

在`org.wltea.analyzer.cfg`下class`DefaultConfig`中可设置参看`PATH_DIC_MAIN`主词典路径和`PATH_DIC_QUANTIFIER`量词路径。

IK支持自定义扩展词库，可以在IKAnalyzer.cfg.xml中配置添加，目前我们网站就是将商品和用户信息导出为词库，并在后台任务中维护，[杉果商场](http://www.sonkwo.com/store/search?utf8=%E2%9C%93&q=&button=)

##Acknowledgements 
感谢IK Analyzer分词器的开发者[林良益](http://linliangyi2007.iteye.com/ )
