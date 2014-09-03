
module TP

  module Prototyped

    def set_method(selector, block)

    end

    def set_property(selector, value)

    end

    def set_prototype(object)
      self.extend RedefineMethodMissing
      self.prototype = object
    end

  end

  module RedefineMethodMissing
    attr_accessor :prototype
    def method_missing(name, *args, &block)
      self.prototype.send name, *args, &block
    end
  end

end

