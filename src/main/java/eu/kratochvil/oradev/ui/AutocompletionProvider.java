package eu.kratochvil.oradev.ui;

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

        return provider;

    }

}
