USE helpr;

SELECT * FROM helpr.chamado;

DELIMITER //

CREATE PROCEDURE listarChamadosAbertosNoDia(DataSelecionada DATE)
BEGIN
	SELECT * FROM helpr.chamado WHERE data_abertura = DataSelecionada;
END//

CALL listarChamadosAbertosNoDia('2022-12-06')