package main.java.abstractsyntaxtree.node.operators.nary.lists;

import java.util.ArrayList;
import java.util.List;

import main.java.abstractsyntaxtree.exceptions.ASTDuplicatedIdentifierException;
import main.java.abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.scopes.Environment;
import main.java.scopes.compiler.EnvironmentCompiler;
import main.java.scopes.compiler.instructions.CodeBlockInstructionsSet;
import main.java.types.IType;
import main.java.types.lists.TList;
import main.java.values.atomics.IValue;
import main.java.values.lists.VList;
import main.java.values.utils.exceptions.NumberArgumentsErrorException;
import main.java.values.utils.exceptions.TypeErrorException;

public class ASTListJoin implements ASTNode {

	private ASTNode elementToJoinASTNodeDescendant;
	
	private ASTNode listASTNodeDescendant;
	
	private static final String TYPE_ERROR_MESSAGE = "Illegal Arguments to :: (list join) operator!!!";
	
	
	public ASTListJoin(ASTNode elementToJoinASTNodeDescendant, ASTNode listASTNodeDescendant) {
		this.elementToJoinASTNodeDescendant = elementToJoinASTNodeDescendant;
		this.listASTNodeDescendant = listASTNodeDescendant;
	}
	
	@Override
	public IValue eval(Environment<IValue> environment)
		   throws ASTInvalidIdentifierException, TypeErrorException, NumberArgumentsErrorException {
		
		IValue elementToJoinASTNodeDescendantValue = this.elementToJoinASTNodeDescendant.eval(environment);
		IValue listASTNodeDescendantValue = this.listASTNodeDescendant.eval(environment);
		
		if( (elementToJoinASTNodeDescendantValue instanceof IValue) && (listASTNodeDescendantValue instanceof VList) ) {
			
			List<IValue> joinedListResult = new ArrayList<IValue>();
			
			joinedListResult.add(elementToJoinASTNodeDescendantValue);
			joinedListResult.addAll( ( (List<IValue>) ( (VList) listASTNodeDescendantValue).getListValues() ) );
		
			return new VList(joinedListResult);
			
		}
		else {
			throw new TypeErrorException(TYPE_ERROR_MESSAGE);
		}
		
	}

	@Override
	public void compile(EnvironmentCompiler environmentCompiler, CodeBlockInstructionsSet codeBlockInstructionsSet)
			throws ASTInvalidIdentifierException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws TypeErrorException, ASTInvalidIdentifierException,
		   NumberArgumentsErrorException, ASTDuplicatedIdentifierException {
		
		IType elementToJoinType = this.elementToJoinASTNodeDescendant.typecheck(environment);
		
		IType listType = this.listASTNodeDescendant.typecheck(environment);
		
		
		if( (elementToJoinType instanceof IType) && (listType instanceof TList) ) {
			
			IType listValuesType = ( (IType) ( (TList) listType ).getListType() );
			
			if(listValuesType.equals(elementToJoinType))  {
				
				return new TList(listValuesType);
				
			}
			else {
				throw new TypeErrorException(TYPE_ERROR_MESSAGE);
			}
		}
		else {
			throw new TypeErrorException(TYPE_ERROR_MESSAGE);
		}
		
	}

}
