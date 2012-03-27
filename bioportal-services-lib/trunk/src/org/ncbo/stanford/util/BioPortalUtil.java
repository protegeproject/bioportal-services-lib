package org.ncbo.stanford.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.ncbo.stanford.bean.ontology.OntologyBean;
import org.ncbo.stanford.bean.search.Page;

public class BioPortalUtil {
    private static Logger logger = Logger.getLogger(BioPortalUtil.class.toString());

    public static String getConceptUrl(String bpRestBase, String ont, String conceptId) {
        try {
           String url = bpRestBase + BioPortalServerConstants.CONCEPTS_REST + "/" + ont + "?" +
                        BioPortalServerConstants.CONCEPT_ID_PARAM + "=" + HTMLUtil.encodeURI(conceptId);
            url = getUrlWithDefaultSuffix(url);
            return url;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static String getRootUrl(String bpRestBase, String ont) {
        String url = bpRestBase + BioPortalServerConstants.CONCEPTS_REST + "/" + ont + "/" + BioPortalServerConstants.ROOTS_REST;
        url = getUrlWithDefaultSuffix(url);
        return url;
    }


    public static Page getSearchResults(String urlString) throws MalformedURLException, IOException {
        URL url = new URL(urlString);
        BioportalSearch bps = new BioportalSearch();
        return bps.getSearchResults(url);
    }

    public static String getDefaultRestSuffix() {
        return BioPortalServerConstants.BP_PRODUCTION_PROTEGE_API_KEY;
    }

    public static String getUrlWithDefaultSuffix(String url) {
        if (!url.contains("?")) {
            url = url + "?";
        } else {
            url = url + "&";
        }
        return url + getDefaultRestSuffix();
    }

    public static String getVisualizationURL(String bpBase, String ontologyId, String conceptId) {
        String str = "";
        try {
            str = bpBase + BioPortalServerConstants.VISUALIZE_REST + "/" + ontologyId + "?conceptid=" + HTMLUtil.encodeURI(conceptId);
        } catch (UnsupportedEncodingException e) {
            logger.log(Level.WARNING, "Error at encoding concept id: " + conceptId, e);
        }
        return str;
    }

    /*
     * Ontology methods
     */

    public static List<OntologyBean> getOntologyList(String bpRestBase) {
        BioportalOntologyList c = new BioportalOntologyList();

        String urlStr = bpRestBase + BioPortalServerConstants.ONTOLOGIES_REST;
        urlStr = BioPortalUtil.getUrlWithDefaultSuffix(urlStr);
        URL url;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            logger.log(Level.WARNING, "Invalid URL to retrieve ontologies from BP: " + bpRestBase + BioPortalServerConstants.ONTOLOGIES_REST);
            return null;
        }
        return c.getOntologyProperties(url);
    }

    public static OntologyBean getOntologyBean(String bpRestBase, String ontId) {
        String urlString = bpRestBase + BioPortalServerConstants.ONTOLOGIES_REST + "/" + ontId;
        urlString = getUrlWithDefaultSuffix(urlString);
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
           logger.log(Level.WARNING, "Malformed URL: " + urlString, e);
           return null;
        }

        return new BioportalOntology().getOntologyProperties(url);
    }

    public static OntologyBean getOntology(String urlString) throws MalformedURLException, IOException {
        URL url = new URL(urlString);
        BioportalOntology bpo = new BioportalOntology();
        return bpo.getOntologyProperties(url);
    }

    public static OntologyBean getViewOnOntology(String bpRestBaseUrl, int viewId, String bpRestCallSuffix) {
        String urlString = bpRestBaseUrl + BioPortalServerConstants.ONTOLOGIES_REST + "/" + viewId;
        urlString = BioPortalUtil.addRestCallSuffixToUrl(urlString, bpRestCallSuffix);
        try {
            return getOntology(urlString);
        } catch (MalformedURLException e) {
            logger.log(Level.WARNING, "Something is wrong with the following URL:" + urlString, e);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Failed to access URL: " + urlString, e);
        }

        return null;
    }

    public static int getViewOnOntologyId(String bpRestBaseUrl, int viewId, String bpRestCallSuffix) {
        int bpViewOnOntologyId = BioPortalViewOntologyMap.UNKNOWN;

        String urlString = bpRestBaseUrl + BioPortalServerConstants.ONTOLOGIES_REST + "/" + viewId;
        urlString = BioPortalUtil.addRestCallSuffixToUrl(urlString, bpRestCallSuffix);
        try {
            bpViewOnOntologyId = BioPortalUtil.getViewOnOntologyId(urlString);
        } catch (MalformedURLException e) {
            logger.log(Level.WARNING, "Something is wrong with the following URL:" + urlString, e);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Failed to access URL: " + urlString, e);
        }

        return bpViewOnOntologyId;
    }

    public static int getViewOnOntologyId(String urlString) throws MalformedURLException, IOException {
        int res = BioPortalViewOntologyMap.UNKNOWN;

        OntologyBean ob = getOntology(urlString);
        if (ob == null) {
            return res;
        } //BP bug

        if (!ob.isView()) {
            res = BioPortalViewOntologyMap.NOT_A_VIEW;
        } else {
            List<Integer> viewOnOntologyVersionIds = ob.getViewOnOntologyVersionId();
            if (viewOnOntologyVersionIds.isEmpty()) {
                res = BioPortalViewOntologyMap.NOT_A_VIEW;
                /*
                 * Note: we could have a different code for this case (something like NOT_PROPERLY_SPECIFIED_VIEW),
                 * but it would not bring us too much benefit, since we still would not know
                 * what ontology id to use instead of the view id, and it would also require extra testing.
                 */
            } else {
                res = viewOnOntologyVersionIds.get(0);
                /*
                 * Note: we could test for multiple values, but the current logic can not deal with views derived from multiple ontologies, so we
                 * consider only the first ontology and hope that the concepts we are interested in are extracted from it.
                 */
                if (viewOnOntologyVersionIds.size() > 1) {
                    System.err.println("WARNING: View is defined on multiple ontologies. This may cause problems! " + urlString);
                }
            }
        }
        return res;
    }

    public static String addRestCallSuffixToUrl(String bpRestUrl, String bpRestCallSuffix) {
        String res = bpRestUrl;
        if (bpRestCallSuffix != null) {
            res += (res.contains("?") ? "&" : "?") + bpRestCallSuffix;
        }

        return res;
    }

}
