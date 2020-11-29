package br.com.bank.count.service.impl;

import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "br.com.bank.count", importOptions = DoNotIncludeTests.class)
public class ServiceTest {
	
//	private static final String SERVICE_PACKAGE = "..service";
//	private static final String SERVICE_IMPL_PACKAGE = "..service.impl";
//	private static final String CONTROLLER_PACKAGE = "..controller";
//	
//	@ArchTest
//	public static final ArchRule service_com_nome_service = classes().that().resideInAnyPackage(SERVICE_PACKAGE).should().haveSimpleNameContaining("SERVICE");

}
