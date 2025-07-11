package com.github.cnrture.quickprojectwizard.projectwizard

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider

class AndroidStudioTemplateProvider : WizardTemplateProvider() {
    override fun getTemplates(): List<Template> = listOf(composeMultiplatformTemplate, composeTemplate, xmlTemplate)
}
