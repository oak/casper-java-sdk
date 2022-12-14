package com.casper.sdk.model.clvalue;

import com.casper.sdk.annotation.ExcludeFromJacocoGeneratedReport;
import com.casper.sdk.exception.NoSuchTypeException;
import com.casper.sdk.model.clvalue.cltype.CLTypeAny;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import dev.oak3.sbs4j.DeserializerBuffer;
import dev.oak3.sbs4j.SerializerBuffer;
import dev.oak3.sbs4j.exception.ValueDeserializationException;
import dev.oak3.sbs4j.exception.ValueSerializationException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Casper Object CLValue implementation
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @see AbstractCLValue
 * @since 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CLValueAny extends AbstractCLValue<Object, CLTypeAny> {
    private CLTypeAny clType = new CLTypeAny();

    @JsonSetter("cl_type")
    @ExcludeFromJacocoGeneratedReport
    protected void setJsonClType(CLTypeAny clType) {
        this.clType = clType;
    }

    @JsonGetter("cl_type")
    @ExcludeFromJacocoGeneratedReport
    protected String getJsonClType() {
        return this.getClType().getTypeName();
    }

    public CLValueAny(Object value) {
        this.setValue(value);
    }

    @Override
    public void serialize(SerializerBuffer ser, boolean encodeType) throws ValueSerializationException, NoSuchTypeException {
        if (this.getValue() == null) return;

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(this.getValue());
            byte[] objectByteArray = bos.toByteArray();
            ser.writeI32(objectByteArray.length);
            ser.writeByteArray(objectByteArray);

            if (encodeType) {
                this.encodeType(ser);
            }
        } catch (IOException e) {
            throw new ValueSerializationException(String.format("Error serializing %s", this.getClass().getSimpleName()), e);
        }
    }

    @Override
    public void deserialize(DeserializerBuffer deser) throws ValueDeserializationException {
        int objectByteArrayLength = deser.readI32();
        try (ByteArrayInputStream bis = new ByteArrayInputStream(deser.readByteArray(objectByteArrayLength));
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            this.setValue(ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            throw new ValueDeserializationException(String.format("Error deserializing %s", this.getClass().getSimpleName()), e);
        }
    }
}
