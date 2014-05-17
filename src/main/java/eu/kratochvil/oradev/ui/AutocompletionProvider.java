package eu.kratochvil.oradev.ui;

import eu.kratochvil.oradev.database.Structure;
import eu.kratochvil.oradev.database.model.TableInfo;
import eu.kratochvil.oradev.database.model.ViewInfo;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.autocomplete.ShorthandCompletion;

/**
 * @author Jiri Kratochvil <jiri.kratochvil@topmonks.com>
 */
public class AutocompletionProvider {

    public CompletionProvider createCompletionProvider() {

        // A DefaultCompletionProvider is the simplest concrete implementation
        // of CompletionProvider. This provider has no understanding of
        // language semantics. It simply checks the text entered up to the
        // caret position for a match against known completions. This is all
        // that is needed in the majority of cases.
        DefaultCompletionProvider provider = new DefaultCompletionProvider();

        // Add completions for all sql
        provider.addCompletion(new BasicCompletion(provider, "select"));
        provider.addCompletion(new BasicCompletion(provider, "from"));
        provider.addCompletion(new BasicCompletion(provider, "delete"));
        provider.addCompletion(new BasicCompletion(provider, "insert"));
        provider.addCompletion(new BasicCompletion(provider, "update"));
        provider.addCompletion(new BasicCompletion(provider, "set"));
        provider.addCompletion(new BasicCompletion(provider, "where"));
        provider.addCompletion(new BasicCompletion(provider, "group by"));
        provider.addCompletion(new BasicCompletion(provider, "orber by"));

        // Add a couple of "shorthand" completions. These completions don't
        // require the input text to be the same thing as the replacement text.
        provider.addCompletion(new ShorthandCompletion(provider, "sf",
                "select * from ", "select * from"));
        provider.addCompletion(new ShorthandCompletion(provider, "ob",
                "order by", "order by"));


        // Add DB objects
        for (TableInfo tableInfo : Structure.getInstance().getTables()) {
            provider.addCompletion(new BasicCompletion(provider, tableInfo.toString()));
        }
        for (ViewInfo viewInfo : Structure.getInstance().getViews()) {
                     provider.addCompletion(new BasicCompletion(provider, viewInfo.toString()));
                 }


        return provider;

    }

}
