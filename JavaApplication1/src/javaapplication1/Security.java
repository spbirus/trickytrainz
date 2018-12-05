/*

* Decompiled with CFR 0_132.

*

 * Could not load the following classes:

*  java.lang.invoke.StringConcatFactory

*/

import java.io.InputStream;

import java.io.PrintStream;

//import java.lang.invoke.StringConcatFactory;

import java.util.Scanner;

 

public class Security {

    public static String[] PODUNK = new String[]{"antelope", "aardvark", "anteater", "alligator", "armadillo", "axolotl", "apoptosis", "ariel", "aspergillum", "astrolabe", "astrobleme", "acknowledge", "antique", "almond", "aster", "canticle", "cashew", "cardamom", "callow", "discord", "amphibian", "cerulean", "countess", "constellation", "coriaceous", "uncouth", "cryptozoology", "cupreous", "cyanic", "diesel", "detergent", "dipsomaniac", "ergometer", "ennui", "elated", "eucatastrope", "flipple", "dabble", "eudaemonic", "euphoric", "utopian", "incunabula", "labarum", "rampant", "sapient", "lycanthropy", "lollygag", "luculent", "muktuk", "somnambulism", "noyade", "lived", "polymath", "paraph", "periapt", "previse", "oracular", "narthex", "pother", "lugubrious", "mellifluous", "prosopagnosia", "rhinoplasty", "sommelier", "toehold", "scofflaw", "shiver", "artichoke", "scallop", "spaghettification", "grumble", "gamble"};

    private static String[] BUMPUS = new String[]{"acre", "acre-foot", "acreage", "acres", "acrid", "acrididae", "acridity", "acridotheres", "acrilan", "acrimonious", "acrimony", "acris", "acritical", "acritude", "acroama", "acroamatic", "acroamatical", "acroamatics", "acroanesthesia", "acroatic", "acrobat", "acrobates", "acrobatic", "acrobatics", "acrocarp", "acrocarpous", "acrocarpus", "acrocentric", "acrocephalus", "acroclinium", "acrocomia", "acrocyanosis", "acrodont", "acrogen", "acrogenic", "acromatic", "acromegalic", "acromegaly", "acromicria", "acromion", "acromphalus", "acromyotonia", "acronym", "acronymic", "acropetal", "acrophobia", "acrophobic", "acropolis", "acropora", "acrosome", "acrospire", "across", "across-the-board", "acrostic", "acrostichum", "acrylic", "act", "acta", "actable", "actaea", "acted", "acti", "actias", "actifed", "actin", "actinal", "acting", "acting", "actinia", "actiniaria", "actinic", "actinidia", "actinidiaceae", "actiniopteris", "actinism", "actinium", "actinoid", "actinolite", "actinomeris", "actinometer", "actinometric", "actinometry", "actinomorphic", "actinomyces", "actinomycetacaea", "actinomycetal", "actinomycetales", "actinomycete", "actinomycin", "actinomycosis", "actinomycotic", "actinomyxidia", "actinomyxidian", "actinopod", "actinopoda", "action", "actionable", "actions", "actitis", "actium", "activated", "activating(a)", "activation", "activator", "active", "actively", "activeness", "activism", "activist", "activity", "actomyosin", "actor", "actress", "acts", "actu", "actual", "actuality", "actualized", "actually", "actuarial", "actuary", "actuateact", "actuated", "actuator", "actum", "actus", "acu", "acuate", "acuity", "aculea", "aculeate", "aculeated", "aculeus", "acumen", "acuminate", "acuminated", "acumination", "acun", "acupressure", "acupuncture", "acute", "acutely", "acuteness", "acyclic", "acyclovir", "ad", "ad-lib", "adactylia", "adactylism", "adactylous", "adad", "adaga", "adage", "adagio", "adalia", "adam", "adamance", "adamant", "adamantean", "adamantine", "adamantly", "adams", "adansonia", "adapa", "adapid", "adapt", "adaptability", "adaptable", "adaptation", "adaptational", "adapted", "adapter", "adaption", "adaptive", "adar", "adaxial", "add", "addable", "addax", "adde", "added", "addend", "addendum", "adder", "addere", "addesse", "addict", "addicted", "addiction", "addictive", "adding", "addison", "additament", "addition", "additional", "additionally", "additive", "additum", "addle", "addle-head", "addlebrained", "addled", "addlehead", "addlepated", "address", "addressable", "addressed", "addressee", "addresses", "adduce", "adducent", "adducing", "adduction", "adductive", "adductor", "ade", "adeem", "adel", "adelaide", "adelges", "adelgid", "adelgidae", "adelie", "adelig", "adelomorphous", "ademption", "ademptum", "aden", "adenanthera", "adenine", "adenitis", "adenium", "adenocarcinoma", "adenocarcinomatous", "adenography", "adenoid", "adenoidal", "adenoidectomy", "adenoma", "adenomegaly", "adenopathy", "adenosine", "adenota", "adenovirus", "adeo", "adeology", "adept", "adeptness", "adequacy", "adequate", "adequately", "adespotic", "adhere", "adherence", "adherent", "adhering", "adhesion", "adhesive", "adhesiveness", "adhibenda", "adhibit", "adhibition", "adhortation", "adiabatic", "adiantaceae", "adiantum", "adiaphanous", "adiathermancy", "adience", "adient", "adieu", "adige", "adipocere", "adipose", "adiposity", "adirondacks", "adit", "aditi", "aditya", "adj", "adjacency", "adjacent", "adjection", "adjectitious", "adjectival", "adjectivally", "adjective", "adjectively", "adjectives", "adjoin", "adjoining", "adjourn", "adjournment", "adjuc", "adjudge", "adjudicate", "adjudication", "adjudicative", "adjudicator", "adjunct", "adjunctive", "adjuration", "adjuratory", "adjure", "adjust", "adjustable", "adjusted", "adjuster", "adjustive", "adjustment", "adjutage", "adjutant", "adjuvant", "adjuvat", "adlumia", "admass", "admeasurement", "administer", "administrable", "administration", "administrative", "administratively", "administrator", "administrators", "admirability", "admirable", "admirably", "admiral", "admiralty", "admirari", "admiration", "admire", "admired", "admirer", "admiring", "admiringly", "admissable", "admissibility", "admissible", "admission", "admissive", "admit", "admittable", "admittance", "admitted", "admitted", "admitting", "admixture", "admlration", "admonish", "admonished", "admonisher", "admonition", "admonitive", "admonitory", "adnate", "adnexa", "adnexal", "adnoun", "ado", "adobe", "adobo", "adolescence", "adolescent", "adonic", "adonis", "adonize", "adopt", "adoptable", "adopted", "adoption", "adoptive", "adorability", "adorable", "adorably", "adoration", "adore", "adored", "adorer", "adoring", "adoringly", "adorn", "adorned", "adorned", "adornment", "adown", "adrenal", "adrenalectomy", "adrenaline", "adrenarche", "adrenergic", "adrenocortical", "adrenocorticotropic", "adrenosterone", "adrenotrophin", "adriatic", "adrift", "adrift", "adroit", "adroitly", "adroitness", "adrolepsy", "adscititious", "adscript", "adscriptus", "adsorbable", "adsorbate", "adsorbed", "adsorbent", "adsorption", "adulation", "adulator", "adulatory", "adullam", "adult", "adulterant", "adulterate", "adulterated", "adulterating", "adulteration", "adulterer", "adulteress", "adulterine", "adulterous", "adulterously", "adultery", "adulthood", "adultism", "adultness", "adultress", "adumbrate", "adumbration", "adumbrative", "aduncated", "aduncity", "aduncous", "adust", "adustion", "adv", "advance", "advance", "advanced", "advanced", "advancement", "advances", "advancing", "advantage", "advantaged", "advantageous", "advection", "advective", "advene", "advent", "adventism", "adventist", "adventitial", "adventitious", "adventive", "adventure", "adventurer", "adventures", "adventuress", "adventurism", "adventuristic", "adventurous", "adventurousness", "adverb", "adverbial", "adverbially", "adverbs", "adversaria", "adversary", "adversarys", "adversative", "adverse", "adversely", "adversis", "adversity", "adversitys", "adversum", "advert", "advertence", "advertency", "advertent", "advertise", "advertised", "advertisement", "advertiser", "advertising", "advice", "advisability", "advisable", "advise", "advised", "advisedly", "advisee", "advisemement", "adviser", "advisory", "advocacy", "advocate", "advocation", "advoutress", "advoutry", "advowson", "adynamia", "adynamic", "adynamy", "adytum", "adz", "adze", "adzooks", "aecial", "aeciospore", "aecium", "aedes", "aedile", "aedipus", "aeequa", "aegean", "aegiceras", "aegilops", "aegina", "aegis", "aegospotami", "aegri", "aegypiidae", "aegypius", "aegyptopithecus", "aeneas", "aeneid", "aeneus", "aeolian", "aeolic", "aeolis", "aeolotropic", "aeolus", "aeon", "aeonium", "aepyceros", "aepyornidae", "aepyorniformes", "aequa", "aequam", "aequat", "aequis", "aequo", "aerated", "aeration", "aerator", "aere", "aerial", "aerialist", "aerially", "aerides", "aerie", "aeriferous", "aerifiction", "aeriform", "aerobacter", "aerobe", "aerobic", "aerobics", "aerobiosis", "aerobiotic", "aerodontalgia", "aerodrome", "aerodynamic", "aerodynamics", "aerography", "aerolite", "aerological", "aerology", "aerolytic", "aeromancy", "aeromechanic", "aeromechanics", "aeromedical", "aeromedicine", "aerometer", "aerometry", "aeronatics", "aeronaut", "aeronautic", "aeronautical", "aeronautics", "aerophagia", "aerophilatelic", "aerophilately", "aeroplane", "aeroplanist", "aeroscope", "aeroscopy", "aerosol", "aerosolized", "aerospace", "aerosphere", "aerostat", "aerostatic", "aerostatics", "aerostation", "aertex", "aes", "aeschylean", "aeschylus", "aeschynanthus", "aesculapian", "aesculapius", "aesculus", "aesir", "aesop", "aestas", "aesthetic", "aesthetically", "aesthetics", "aestival", "aetas", "aeterna", "aeternum", "aether", "aethionema", "aethusa", "aetiology", "aetobatus", "aevi", "afar", "afeard", "afeard", "afebrile", "affability", "affable", "affably", "affair", "affaire", "affaires", "affairs", "affect", "affectation", "affected", "affected", "affectedly", "affectedness", "affectibility", "affecting", "affectingly", "affection", "affectional", "affectionate", "affectionateness", "affectioned", "affections", "affector", "affects", "affenpinscher", "afferent", "affettuoso", "affiance", "affianced", "affiche", "afficher", "affidation", "affidavit", "affiliated", "affiliation", "affinal", "affined", "affinity", "affirm", "affirmable", "affirmance", "affirmation", "affirmative", "affirmatively", "affirmativeness", "affix", "affixal", "affixation", "affixed", "afflation", "afflatus", "afflict", "afflicted", "afflicting", "affliction", "afflictions", "afflictive", "affluence", "affluent", "afflux", "affluxion", "afford", "afforestation", "affraid", "affranchise", "affranchisement", "affray", "affrayment", "affricate", "affrication", "affriction", "affright", "affrightment", "affront", "affronted", "affronterai", "affuse", "affusion", "afghan", "afghani", "afghanistan", "afibrinogenemia", "afield", "afire", "aflak", "aflame", "aflare", "afloat", "afloat", "aflutter", "afoot", "afoot", "afore", "aforehand", "aforementioned", "aforenamed", "aforesaid", "aforesaid", "aforethought", "aforethought", "afoul", "afoul", "afraid", "afraid", "aframomum", "afreet", "afresh", "afric", "africa", "african", "african-american", "africander", "afrikaans", "afrikaner", "afro", "afro-asian", "afro-wig", "afroasiatic", "afrocarpus", "afropavo", "aft", "aft", "after", "after", "after-hours", "after-school", "after-shave", "afterage", "afterbirth", "afterburden", "afterburner", "aftercare", "afterclap", "aftercome", "aftercourse", "aftercrop", "afterdamp", "afterdeck", "afterdinner", "aftereffect", "aftergame", "afterglow", "aftergrowth", "afterimage", "afterlife", "aftermath", "aftermost", "afternoon", "afternoon", "afterpains", "afterpart", "afterpiece", "aftershaft", "aftershafted", "aftershock", "aftertaste", "afterthought", "afterwards", "afterworld", "aga", "agacerie", "again", "agains", "against", "agalactia", "agalloch", "agallochium", "agama", "agamemnon", "agamic", "agamid", "agamidae", "agamist", "agammaglobulinemia", "agapanthus", "agape", "agape", "agapemone", "agapornis", "agar", "agaric", "agaricaceae", "agaricales", "agaricus", "agas", "agastache", "agate", "agateware", "agathis", "agavaceae", "agave", "agaze", "agdistis", "age", "age-old", "aged", "aged", "agedness", "ageism", "agelaius", "ageless", "agelessness", "agelong", "agency", "agenda", "agendum", "agenesis", "agent", "agential", "agentive", "agents", "agentship", "agerasia", "ageratina", "ageratum", "ages", "agglomerate", "agglomeration", "agglutinate", "agglutination", "agglutinative", "agglutinin", "agglutinogen", "aggrandize", "aggrandizement", "aggravable", "aggravate", "aggravated", "aggravating", "aggravatingly", "aggravation", "aggregate", "aggregation", "aggression", "aggressive", "aggressively", "aggressiveness", "aggressor", "aggrieve", "aggrieved", "aggro", "aggroup", "aghan", "aghast", "aghast", "agianst", "agile", "agilely", "agility", "agincourt", "aging", "agio", "agiotage"};

    public static int CORRECT_NUM_OFFSET = 2;

    public static String CORRECT_WORD = "callow";

    public static String INCORRECT_WORD = "rhinoplasty";

 

    public static void printArray(char[] arrc) {

        System.out.print("[ ");

        for (int i = 0; i < arrc.length - 1; ++i) {

//            System.out.print((String)((Object)StringConcatFactory.makeConcatWithConstants(new Object[]{"\u0001, "}, arrc[i])));

        }

//        System.out.println((String)((Object)StringConcatFactory.makeConcatWithConstants(new Object[]{"\u0001 ]"}, arrc[arrc.length - 1])));

    }

 

    public static int wooble(int n) {

        int n2 = n;

        int n3 = 2;

        int n4 = 3;

        int n5 = 4;

        int n6 = 5;

        int n7 = 9001;

        for (int i = 0; i < 10; ++i) {

            n7 += n2 + n3 + n4 + n5 + n6;

            ++n6;

            --n2;

            n3 += 2;

            n4 -= 2;

            n5 = 8;

        }

        return n7;

    }

 

    public static int wooble2(int n) {

        int n2 = n;

        int n3 = -2;

        int n4 = -3;

        int n5 = -4;

        int n6 = -5;

        int n7 = 56535;

        for (int i = 0; i < 10; ++i) {

            n7 += n2 + n3 + n4 + n5 + n6;

            ++n6;

            --n2;

            n3 += 3;

            n4 -= 2;

            n5 = 8;

        }

        return n7;

    }

 

    public static int wooble3(int n, int n2, int n3, int n4) {

        if (n == 4) {

            n2 = n3;

        } else if (n < 0) {

            n3 = n4;

        } else if (n3 == n4) {

            n4 = 0;

        } else {

            n = 4;

            n2 = 2;

        }

        return n + n2 + n3 + n4;

    }

 

    public static int wooble4(String string, int n) {

        int n2 = Security.wooble3(n);

        n = string == null ? 0 : ++n;

        int n3 = Security.wooble(10);

        for (int i = 0; i < 100; ++i) {

            n3 = i + Security.wooble2(i);

        }

        n3 = Math.abs(n3) % PODUNK.length;

        return n3;

    }

 

    public static boolean wooble5(String string, int n, int n2) {

        if (n > 0) {

            n = Math.abs(n);

            n2 = n - n;

        } else if (n < 100) {

            n = Math.abs(n);

        } else {

            n2 = Math.abs(0);

        }

        String string2 = PODUNK[n + n2];
        System.out.println("string2: " + string2 + " string: " + string);
        boolean bl = Security.wooble7(string2, string);

        return !bl;

    }

 

    public static boolean wooble7(String string, String string2) {

        try {

            int n;

            char[] arrc = string.toCharArray();

            int n2 = 4;

            char[] arrc2 = BUMPUS[661].toCharArray();

            for (int i = 0; i < 4; ++i) {

                if (i <= 0) {

                    arrc2[i] = arrc[1];

                    continue;

                }

                if (i + 5 < 7) {

                    arrc2[i] = arrc2[6];

                    continue;

                }

                if (i == 2) {

                    arrc2[i] = arrc2[3];

                    continue;

                }

                arrc2[3] = arrc[i - 3];

            }

            char[] arrc3 = new char[n2];

            for (n = 0; n < n2; ++n) {

                arrc3[n] = arrc2[n];

            }

            arrc = string2.toCharArray();

            if (arrc.length != arrc3.length) {

                return true;

            }

            n = arrc.length - 1;

            boolean bl = false;

            for (int i = 0; i < arrc.length; ++i) {

                if (Character.toUpperCase(arrc[i]) == Character.toUpperCase(arrc3[n - i])) continue;

                bl = !bl;

                break;

            }

            return bl;

        }

        catch (Exception exception) {

            return true;

        }

    }

 

    public static int wooble3(int n) {

        int n2 = n * 2;

        int n3 = 1;

        int n4 = 1;

        int n5 = 1;

        int n6 = 1;

        int n7 = 56535;

        for (int i = 0; i < 10; ++i) {

            n7 += n2 + n3 + n4 + n5 + n6;

            ++n6;

            --n2;

            n3 += 3;

            n4 -= 2;

            n5 = 8;

        }

        return n7;

    }

 

    public static void wooble6(int n) {

        int n2 = 0;

        for (int i = 0; i < n; ++i) {

            for (int j = 0; j < 1000000; ++j) {

                n2 += Security.wooble(Security.wooble2(Security.wooble3(j)));

            }

            if (n2 < 0) {

                n2 = 0;

            }

            System.out.print(".");

        }

        System.out.println();

    }

 

    public static void main(String[] arrstring) {

        boolean bl = false;

        Scanner scanner = new Scanner(System.in);

        String string = null;

        int n = 0;

        System.out.print("Establishing connection to mainframe");

        Security.wooble6(5);

        System.out.print("Developing Visual Basic GUI to track IP");

        Security.wooble6(5);

        System.out.print("Decrypting mutexes for object-oriented analysis");

        Security.wooble6(5);

        int test = 0;
        while (!bl) {

            int n2 = Security.wooble(n++);

            System.out.print("ENTER SECRET WORD > ");

            string = BUMPUS[test++];

            int n3 = Security.wooble4(string, n);
            System.out.println("n3: " + n3);
            if (Security.wooble5(string, n3, n3)) {

                System.out.println("ACCESS GRANTED");
//                System.out.println((String)((Object)StringConcatFactory.makeConcatWithConstants(new Object[]{"ACCESS GRANTED: \u0001"}, string.toUpperCase())));

                bl = true;

                continue;

            }

            System.out.println("ACCESS DENIED!!!");

        }

    }

}