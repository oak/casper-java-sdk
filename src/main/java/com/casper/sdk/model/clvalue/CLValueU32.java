package com.casper.sdk.model.clvalue;

import com.casper.sdk.annotation.ExcludeFromJacocoGeneratedReport;
import com.casper.sdk.exception.NoSuchTypeException;
import com.casper.sdk.model.clvalue.cltype.CLTypeU32;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import dev.oak3.sbs4j.DeserializerBuffer;
import dev.oak3.sbs4j.SerializerBuffer;
import dev.oak3.sbs4j.exception.ValueDeserializationException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Casper U32 CLValue implementation
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @see AbstractCLValue
 * @since 0.0.1
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CLValueU32 extends AbstractCLValue<Long, CLTypeU32> {
    private CLTypeU32 clType = new CLTypeU32();

    @JsonSetter("cl_type")
    @ExcludeFromJacocoGeneratedReport
    protected void setJsonClType(CLTypeU32 clType) {
        this.clType = clType;
    }

    @JsonGetter("cl_type")
    @ExcludeFromJacocoGeneratedReport
    protected String getJsonClType() {
        return this.getClType().getTypeName();
    }

    public CLValueU32(Long value) {
        this.setValue(value);
    }

    @Override
    public void serialize(SerializerBuffer ser, boolean encodeType) throws NoSuchTypeException {
        if (this.getValue() == null) return;

        ser.writeU32(this.getValue());

        if (encodeType) {
            this.encodeType(ser);
        }
    }

    @Override
    public void deserialize(DeserializerBuffer deser) throws ValueDeserializationException {
        this.setValue(deser.readU32());
    }
}
