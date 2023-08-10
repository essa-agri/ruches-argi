package com.agri40.filahati.notification.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.agri40.filahati.notification.IntegrationTest;
import com.agri40.filahati.notification.domain.Alert;
import com.agri40.filahati.notification.domain.enumeration.AlertConditionType;
import com.agri40.filahati.notification.domain.enumeration.AlertSi;
import com.agri40.filahati.notification.domain.enumeration.AlertType;
import com.agri40.filahati.notification.repository.AlertRepository;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Integration tests for the {@link AlertResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AlertResourceIT {

    private static final AlertType DEFAULT_TYPE = AlertType.LIMIT;
    private static final AlertType UPDATED_TYPE = AlertType.VARIATION;

    private static final AlertSi DEFAULT_ALERT_SI = AlertSi.POIDS;
    private static final AlertSi UPDATED_ALERT_SI = AlertSi.TEMPERATURE;

    private static final AlertConditionType DEFAULT_CONDITION_TYPE = AlertConditionType.INFERIEURE;
    private static final AlertConditionType UPDATED_CONDITION_TYPE = AlertConditionType.SUPERIEURE;

    private static final Float DEFAULT_VALEUR = 1F;
    private static final Float UPDATED_VALEUR = 2F;

    private static final Float DEFAULT_VARIATION = 1F;
    private static final Float UPDATED_VARIATION = 2F;

    private static final Boolean DEFAULT_STATUT = false;
    private static final Boolean UPDATED_STATUT = true;

    private static final String DEFAULT_RUCHE_ID = "AAAAAAAAAA";
    private static final String UPDATED_RUCHE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_STREAM = "AAAAAAAAAA";
    private static final String UPDATED_LAST_STREAM = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/alerts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private MockMvc restAlertMockMvc;

    private Alert alert;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Alert createEntity() {
        Alert alert = new Alert()
            .type(DEFAULT_TYPE)
            .alertSi(DEFAULT_ALERT_SI)
            .conditionType(DEFAULT_CONDITION_TYPE)
            .valeur(DEFAULT_VALEUR)
            .variation(DEFAULT_VARIATION)
            .statut(DEFAULT_STATUT)
            .rucheId(DEFAULT_RUCHE_ID)
            .lastStream(DEFAULT_LAST_STREAM);
        return alert;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Alert createUpdatedEntity() {
        Alert alert = new Alert()
            .type(UPDATED_TYPE)
            .alertSi(UPDATED_ALERT_SI)
            .conditionType(UPDATED_CONDITION_TYPE)
            .valeur(UPDATED_VALEUR)
            .variation(UPDATED_VARIATION)
            .statut(UPDATED_STATUT)
            .rucheId(UPDATED_RUCHE_ID)
            .lastStream(UPDATED_LAST_STREAM);
        return alert;
    }

    @BeforeEach
    public void initTest() {
        alertRepository.deleteAll();
        alert = createEntity();
    }

    @Test
    void createAlert() throws Exception {
        int databaseSizeBeforeCreate = alertRepository.findAll().size();
        // Create the Alert
        restAlertMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(alert)))
            .andExpect(status().isCreated());

        // Validate the Alert in the database
        List<Alert> alertList = alertRepository.findAll();
        assertThat(alertList).hasSize(databaseSizeBeforeCreate + 1);
        Alert testAlert = alertList.get(alertList.size() - 1);
        assertThat(testAlert.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testAlert.getAlertSi()).isEqualTo(DEFAULT_ALERT_SI);
        assertThat(testAlert.getConditionType()).isEqualTo(DEFAULT_CONDITION_TYPE);
        assertThat(testAlert.getValeur()).isEqualTo(DEFAULT_VALEUR);
        assertThat(testAlert.getVariation()).isEqualTo(DEFAULT_VARIATION);
        assertThat(testAlert.getStatut()).isEqualTo(DEFAULT_STATUT);
        assertThat(testAlert.getRucheId()).isEqualTo(DEFAULT_RUCHE_ID);
        assertThat(testAlert.getLastStream()).isEqualTo(DEFAULT_LAST_STREAM);
    }

    @Test
    void createAlertWithExistingId() throws Exception {
        // Create the Alert with an existing ID
        alert.setId("existing_id");

        int databaseSizeBeforeCreate = alertRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAlertMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(alert)))
            .andExpect(status().isBadRequest());

        // Validate the Alert in the database
        List<Alert> alertList = alertRepository.findAll();
        assertThat(alertList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = alertRepository.findAll().size();
        // set the field null
        alert.setType(null);

        // Create the Alert, which fails.

        restAlertMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(alert)))
            .andExpect(status().isBadRequest());

        List<Alert> alertList = alertRepository.findAll();
        assertThat(alertList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkAlertSiIsRequired() throws Exception {
        int databaseSizeBeforeTest = alertRepository.findAll().size();
        // set the field null
        alert.setAlertSi(null);

        // Create the Alert, which fails.

        restAlertMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(alert)))
            .andExpect(status().isBadRequest());

        List<Alert> alertList = alertRepository.findAll();
        assertThat(alertList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkConditionTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = alertRepository.findAll().size();
        // set the field null
        alert.setConditionType(null);

        // Create the Alert, which fails.

        restAlertMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(alert)))
            .andExpect(status().isBadRequest());

        List<Alert> alertList = alertRepository.findAll();
        assertThat(alertList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkValeurIsRequired() throws Exception {
        int databaseSizeBeforeTest = alertRepository.findAll().size();
        // set the field null
        alert.setValeur(null);

        // Create the Alert, which fails.

        restAlertMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(alert)))
            .andExpect(status().isBadRequest());

        List<Alert> alertList = alertRepository.findAll();
        assertThat(alertList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkStatutIsRequired() throws Exception {
        int databaseSizeBeforeTest = alertRepository.findAll().size();
        // set the field null
        alert.setStatut(null);

        // Create the Alert, which fails.

        restAlertMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(alert)))
            .andExpect(status().isBadRequest());

        List<Alert> alertList = alertRepository.findAll();
        assertThat(alertList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkRucheIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = alertRepository.findAll().size();
        // set the field null
        alert.setRucheId(null);

        // Create the Alert, which fails.

        restAlertMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(alert)))
            .andExpect(status().isBadRequest());

        List<Alert> alertList = alertRepository.findAll();
        assertThat(alertList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllAlerts() throws Exception {
        // Initialize the database
        alertRepository.save(alert);

        // Get all the alertList
        restAlertMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(alert.getId())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].alertSi").value(hasItem(DEFAULT_ALERT_SI.toString())))
            .andExpect(jsonPath("$.[*].conditionType").value(hasItem(DEFAULT_CONDITION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].valeur").value(hasItem(DEFAULT_VALEUR.doubleValue())))
            .andExpect(jsonPath("$.[*].variation").value(hasItem(DEFAULT_VARIATION.doubleValue())))
            .andExpect(jsonPath("$.[*].statut").value(hasItem(DEFAULT_STATUT.booleanValue())))
            .andExpect(jsonPath("$.[*].rucheId").value(hasItem(DEFAULT_RUCHE_ID)))
            .andExpect(jsonPath("$.[*].lastStream").value(hasItem(DEFAULT_LAST_STREAM)));
    }

    @Test
    void getAlert() throws Exception {
        // Initialize the database
        alertRepository.save(alert);

        // Get the alert
        restAlertMockMvc
            .perform(get(ENTITY_API_URL_ID, alert.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(alert.getId()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.alertSi").value(DEFAULT_ALERT_SI.toString()))
            .andExpect(jsonPath("$.conditionType").value(DEFAULT_CONDITION_TYPE.toString()))
            .andExpect(jsonPath("$.valeur").value(DEFAULT_VALEUR.doubleValue()))
            .andExpect(jsonPath("$.variation").value(DEFAULT_VARIATION.doubleValue()))
            .andExpect(jsonPath("$.statut").value(DEFAULT_STATUT.booleanValue()))
            .andExpect(jsonPath("$.rucheId").value(DEFAULT_RUCHE_ID))
            .andExpect(jsonPath("$.lastStream").value(DEFAULT_LAST_STREAM));
    }

    @Test
    void getNonExistingAlert() throws Exception {
        // Get the alert
        restAlertMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingAlert() throws Exception {
        // Initialize the database
        alertRepository.save(alert);

        int databaseSizeBeforeUpdate = alertRepository.findAll().size();

        // Update the alert
        Alert updatedAlert = alertRepository.findById(alert.getId()).orElseThrow();
        updatedAlert
            .type(UPDATED_TYPE)
            .alertSi(UPDATED_ALERT_SI)
            .conditionType(UPDATED_CONDITION_TYPE)
            .valeur(UPDATED_VALEUR)
            .variation(UPDATED_VARIATION)
            .statut(UPDATED_STATUT)
            .rucheId(UPDATED_RUCHE_ID)
            .lastStream(UPDATED_LAST_STREAM);

        restAlertMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAlert.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAlert))
            )
            .andExpect(status().isOk());

        // Validate the Alert in the database
        List<Alert> alertList = alertRepository.findAll();
        assertThat(alertList).hasSize(databaseSizeBeforeUpdate);
        Alert testAlert = alertList.get(alertList.size() - 1);
        assertThat(testAlert.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testAlert.getAlertSi()).isEqualTo(UPDATED_ALERT_SI);
        assertThat(testAlert.getConditionType()).isEqualTo(UPDATED_CONDITION_TYPE);
        assertThat(testAlert.getValeur()).isEqualTo(UPDATED_VALEUR);
        assertThat(testAlert.getVariation()).isEqualTo(UPDATED_VARIATION);
        assertThat(testAlert.getStatut()).isEqualTo(UPDATED_STATUT);
        assertThat(testAlert.getRucheId()).isEqualTo(UPDATED_RUCHE_ID);
        assertThat(testAlert.getLastStream()).isEqualTo(UPDATED_LAST_STREAM);
    }

    @Test
    void putNonExistingAlert() throws Exception {
        int databaseSizeBeforeUpdate = alertRepository.findAll().size();
        alert.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAlertMockMvc
            .perform(
                put(ENTITY_API_URL_ID, alert.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(alert))
            )
            .andExpect(status().isBadRequest());

        // Validate the Alert in the database
        List<Alert> alertList = alertRepository.findAll();
        assertThat(alertList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchAlert() throws Exception {
        int databaseSizeBeforeUpdate = alertRepository.findAll().size();
        alert.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAlertMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(alert))
            )
            .andExpect(status().isBadRequest());

        // Validate the Alert in the database
        List<Alert> alertList = alertRepository.findAll();
        assertThat(alertList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamAlert() throws Exception {
        int databaseSizeBeforeUpdate = alertRepository.findAll().size();
        alert.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAlertMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(alert)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Alert in the database
        List<Alert> alertList = alertRepository.findAll();
        assertThat(alertList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateAlertWithPatch() throws Exception {
        // Initialize the database
        alertRepository.save(alert);

        int databaseSizeBeforeUpdate = alertRepository.findAll().size();

        // Update the alert using partial update
        Alert partialUpdatedAlert = new Alert();
        partialUpdatedAlert.setId(alert.getId());

        partialUpdatedAlert
            .conditionType(UPDATED_CONDITION_TYPE)
            .valeur(UPDATED_VALEUR)
            .variation(UPDATED_VARIATION)
            .rucheId(UPDATED_RUCHE_ID);

        restAlertMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAlert.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAlert))
            )
            .andExpect(status().isOk());

        // Validate the Alert in the database
        List<Alert> alertList = alertRepository.findAll();
        assertThat(alertList).hasSize(databaseSizeBeforeUpdate);
        Alert testAlert = alertList.get(alertList.size() - 1);
        assertThat(testAlert.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testAlert.getAlertSi()).isEqualTo(DEFAULT_ALERT_SI);
        assertThat(testAlert.getConditionType()).isEqualTo(UPDATED_CONDITION_TYPE);
        assertThat(testAlert.getValeur()).isEqualTo(UPDATED_VALEUR);
        assertThat(testAlert.getVariation()).isEqualTo(UPDATED_VARIATION);
        assertThat(testAlert.getStatut()).isEqualTo(DEFAULT_STATUT);
        assertThat(testAlert.getRucheId()).isEqualTo(UPDATED_RUCHE_ID);
        assertThat(testAlert.getLastStream()).isEqualTo(DEFAULT_LAST_STREAM);
    }

    @Test
    void fullUpdateAlertWithPatch() throws Exception {
        // Initialize the database
        alertRepository.save(alert);

        int databaseSizeBeforeUpdate = alertRepository.findAll().size();

        // Update the alert using partial update
        Alert partialUpdatedAlert = new Alert();
        partialUpdatedAlert.setId(alert.getId());

        partialUpdatedAlert
            .type(UPDATED_TYPE)
            .alertSi(UPDATED_ALERT_SI)
            .conditionType(UPDATED_CONDITION_TYPE)
            .valeur(UPDATED_VALEUR)
            .variation(UPDATED_VARIATION)
            .statut(UPDATED_STATUT)
            .rucheId(UPDATED_RUCHE_ID)
            .lastStream(UPDATED_LAST_STREAM);

        restAlertMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAlert.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAlert))
            )
            .andExpect(status().isOk());

        // Validate the Alert in the database
        List<Alert> alertList = alertRepository.findAll();
        assertThat(alertList).hasSize(databaseSizeBeforeUpdate);
        Alert testAlert = alertList.get(alertList.size() - 1);
        assertThat(testAlert.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testAlert.getAlertSi()).isEqualTo(UPDATED_ALERT_SI);
        assertThat(testAlert.getConditionType()).isEqualTo(UPDATED_CONDITION_TYPE);
        assertThat(testAlert.getValeur()).isEqualTo(UPDATED_VALEUR);
        assertThat(testAlert.getVariation()).isEqualTo(UPDATED_VARIATION);
        assertThat(testAlert.getStatut()).isEqualTo(UPDATED_STATUT);
        assertThat(testAlert.getRucheId()).isEqualTo(UPDATED_RUCHE_ID);
        assertThat(testAlert.getLastStream()).isEqualTo(UPDATED_LAST_STREAM);
    }

    @Test
    void patchNonExistingAlert() throws Exception {
        int databaseSizeBeforeUpdate = alertRepository.findAll().size();
        alert.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAlertMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, alert.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(alert))
            )
            .andExpect(status().isBadRequest());

        // Validate the Alert in the database
        List<Alert> alertList = alertRepository.findAll();
        assertThat(alertList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchAlert() throws Exception {
        int databaseSizeBeforeUpdate = alertRepository.findAll().size();
        alert.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAlertMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(alert))
            )
            .andExpect(status().isBadRequest());

        // Validate the Alert in the database
        List<Alert> alertList = alertRepository.findAll();
        assertThat(alertList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamAlert() throws Exception {
        int databaseSizeBeforeUpdate = alertRepository.findAll().size();
        alert.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAlertMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(alert)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Alert in the database
        List<Alert> alertList = alertRepository.findAll();
        assertThat(alertList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteAlert() throws Exception {
        // Initialize the database
        alertRepository.save(alert);

        int databaseSizeBeforeDelete = alertRepository.findAll().size();

        // Delete the alert
        restAlertMockMvc
            .perform(delete(ENTITY_API_URL_ID, alert.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Alert> alertList = alertRepository.findAll();
        assertThat(alertList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
