package util;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import edu.stanford.nlp.ling.CategoryWordTag;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeGraphNode;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.trees.TypedDependency;
import edu.stanford.nlp.trees.international.pennchinese.ChineseTreebankLanguagePack;

public class ParserWrapper {
	private static LexicalizedParser lp;
	
	static {
		lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/xinhuaPCFG.ser.gz");
	}
	
	public List<TypedDependency> getDependency(String[] sent){
		List<CoreLabel> rawWords = Sentence.toCoreLabelList(sent);
		Tree parse = lp.apply(rawWords);
		TreebankLanguagePack tlp = new ChineseTreebankLanguagePack();
		GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
		GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
		List<TypedDependency> tdl = gs.typedDependenciesCCprocessed();
		return tdl;
	}
	
	public List<TypedDependency> getDependency(POSNode[] posNodes){
		String[] sent = new String[posNodes.length];
		for(int i=0;i<posNodes.length;i++){
			sent[i] = posNodes[i].getWord();
		}
		
		return getDependency(sent);
	}
	
	public String[][] transformToDependencyGraph(List<TypedDependency> tdl, int size){
		String[][] dependencyGraph = new String[size][size];
		for(int i=1;i<size;i++)
			for(int j=1;j<size;j++)
				dependencyGraph[i][j] = "";
		
		for(TypedDependency td : tdl){
			TreeGraphNode gov = td.gov();
			TreeGraphNode dep = td.dep();
	
			dependencyGraph[gov.index()][dep.index()] = td.reln().getShortName();
			dependencyGraph[dep.index()][gov.index()] = td.reln().getShortName();
		}
		
		return dependencyGraph;
	}
}
