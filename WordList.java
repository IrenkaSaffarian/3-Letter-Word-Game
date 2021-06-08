package com.example.thegame;

import org.junit.Test;


public class WordList {

    public static String[] threeLetterWords = {"aah", "aal", "aas", "aba", "abb", "abo", "abs", "aby", "ace", "ach", "act", "add", "ado", "ads", "adz", "aff", "aft", "aga", "age", "ago", "ags", "aha", "ahi", "ahs", "aid", "ail", "aim", "ain", "air", "ais", "ait", "aka", "ake", "ala", "alb", "ale", "alf", "all", "alp", "als", "alt", "ama", "ami", "amp", "amu", "ana", "and", "ane", "ani", "ann", "ant", "any", "ape", "apo", "app", "apt", "arb", "arc", "ard", "are", "arf", "ark", "arm", "eta", "eth", "euk", "eve", "evo", "ewe", "ewk", "ewt", "exo", "eye", "faa", "fab", "fad", "fae", "fag", "fah", "fan", "fap", "far", "fas", "fat", "faw", "fax", "fay", "fed", "fee", "feg", "feh", "fem", "fen", "fer", "fes", "fet", "feu", "few", "fey", "fez", "fib", "fid", "fie", "fig", "fil", "fin", "fir", "fit", "fix", "fiz", "flu", "fly", "fob", "foe", "fog", "foh", "fon", "fop", "for", "fou", "fox", "foy", "fra", "fro", "fry", "fub", "fud", "meu", "mew", "mho", "mib", "mic", "mid", "mig", "mil", "mim", "mir", "mis", "mix", "miz", "mna", "moa", "mob", "moc", "mod", "moe", "mog", "moi", "mol", "mom", "mon", "moo", "mop", "mor", "mos", "mot", "mou", "mow", "moy", "moz", "mud", "mug", "mum", "mun", "mus", "mut", "mux", "myc", "nab", "nae", "nag", "nah", "nam", "nan", "nap", "nas", "nat", "naw", "nay", "neb", "ned", "nee", "nef", "neg", "nek", "nep", "net", "new", "nib", "nid", "nie", "pop", "pos", "pot", "pow", "pox", "poz", "pre", "pro", "pry", "psi", "pst", "pub", "pud", "pug", "puh", "pul", "pun", "pup", "pur", "pus", "put", "puy", "pya", "pye", "qat", "qis", "qua", "rad", "rag", "rah", "rai", "raj", "ram", "ran", "rap", "ras", "rat", "raw", "rax", "ray", "reb", "rec", "red", "ree", "ref", "reg", "reh", "rei", "rem", "ren", "reo", "rep", "res", "ret", "rev", "rew", "rex", "rez", "rho", "rhy", "ria", "rib", "rid", "toc", "tod", "toe", "tog", "tom", "ton", "too", "top", "tor", "tot", "tow", "toy", "try", "tsk", "tub", "tug", "tui", "tum", "tun", "tup", "tut", "tux", "twa", "two", "twp", "tye", "tyg", "udo", "uds", "uey", "ufo", "ugh", "ugs", "uke", "ule", "ulu", "umm", "ump", "umu", "uni", "uns", "upo", "ups", "urb", "urd", "ure", "urn", "urp", "use", "uta", "ute", "uts", "utu", "uva", "vac", "vae", "vag", "van", "var", "vas", "vat", "vau", "vav", "vaw", "yew", "yex", "ygo", "yid", "yin", "yip", "yob", "yod", "yok", "yom", "yon", "yos", "you", "yow", "yug", "yuk", "yum", "yup", "yus", "zag", "zap", "zas", "zax", "zea", "zed", "zee", "zek", "zel", "zep", "zex", "zho", "zig", "zin", "zip", "zit", "ziz", "zoa", "zol", "zoo", "zos", "zuz"};

    public String[] getList()
    {
        return threeLetterWords;
    }

@Test
    public void main()
    {
        WordList wordlist= new WordList();

        for (int i=0; i<500; i++)
        {
            Guesser guessBoy = new Guesser(wordlist);
            guessBoy.takeGuess();
        }
    }
}
